package kineticnetwork.net.chatfilter.Util;

import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.FileEditor.Reports;
import kineticnetwork.net.chatfilter.MuteHandler.AddMute;
import kineticnetwork.net.chatfilter.Notfiy;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TextCheck {
    Notfiy notfiy = new Notfiy();
    AddMute addMute = new AddMute();
    Reports reports = new Reports();

    private String extra = "null";//Any extra things such as sign cords

    public boolean checkmessage(String input, Player player, String source, String extra) throws IOException {
        String[] sepword = input.split("\\s+");

        for (int i = 0; i < sepword.length; i++) {

            Iterator hmIterator = ChatFilter.Blacklisted.entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                if (sepword[i].contains((CharSequence) mapElement.getKey())) {
                    if (source != "Chat") {
                        //  ChatFilter.getInstance().getLogger().info("Hit check");
                        mutePlayer(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return true;
                    }
                    if (sepword[i].equalsIgnoreCase(String.valueOf(mapElement.getKey()))) { // word is by it self blocked use method1
                        mutePlayer(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return true;
                    }
                    if (ChatFilter.MuteMethod == 2) {
                        possibleflag(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return true;
                    }
                    return true;
                }

            }

        }


        return false;
    }

    public boolean mutePlayer(Player player, String key, String value, String source, String extra, String input) throws IOException {
        reports.report(player.getName(), key, input, source, value);// adds report
        addMute.addMute(player);// mutes player
        notfiy.sendresponse(player, value);// sends mute message
        notfiy.staff(player, input, key, value, source, extra, true);
        return true;
    }

    public boolean possibleflag(Player player, String key, String value, String source, String extra, String input) throws IOException { //Used for chat if word is contained with in another word
        reports.report(player.getName(), key, input, source, value);// adds report
        addMute.addwatch(player);// mutes player
        notfiy.staff(player, input, key, value, source, extra, false);
        return true;
    }
}



