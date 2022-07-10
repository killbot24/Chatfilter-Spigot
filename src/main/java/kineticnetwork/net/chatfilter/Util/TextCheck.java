package kineticnetwork.net.chatfilter.Util;


import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.FileEditor.Reports;
import kineticnetwork.net.chatfilter.MuteHandler.AddMute;
import kineticnetwork.net.chatfilter.Notfiy;
import org.bukkit.entity.Player;


import java.io.Console;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getLogger;

public class TextCheck {
    Notfiy notfiy = new Notfiy();
    AddMute addMute = new AddMute();
    Reports reports = new Reports();

    private String extra = "null";//Any extra things such as sign cords


    public int checkmessage(String input, Player player, String source, String extra) throws IOException {
     //   Logger.getLogger("Debug").info(player.getName());
        String[] sepword = removeInvalids(input).split("\\s+");

        for (int i = 0; i < sepword.length; i++) {

            Iterator hmIterator = ChatFilter.Blacklisted.entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                if (sepword[i].contains((CharSequence) mapElement.getKey())) {
                    if (source != "Chat") {
                        mutePlayer(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                         return 2;
                    }
                    if (sepword[i].equalsIgnoreCase(String.valueOf(mapElement.getKey()))) { // word is by it self blocked use method1
                       // notfiy.sendresponse(player, mapElement.getValue().toString());// sends mute message
                        mutePlayer(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return 2;
                    } else {
                        possibleflag(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return 1;
                    }
                }

            }

        }
        return 0;
    }

    public boolean mutePlayer(Player player, String key, String value, String source, String extra, String input) throws IOException {
        reports.report(player.getName(), key, input, source, value);// adds report
        addMute.addMute(player);// mutes player
        notfiy.sendresponse(player, value);// sends mute message

        if (!value.contains("Blacklisted Term")) {
            ChatFilter.getInstance().getLogger().info( "[Chat Filter]:" + player.getName() + " Has attempted to say "+value);
            notfiy.staff(player, input, key, value, source, extra, true);

        }else {
            ChatFilter.getInstance().getLogger().info( "[Chat Filter]:" + player.getName() + " Has attempted to say a blacklistedTerm");
            notfiy.staff(player, "Message removed for security", "BlackListed Term", "Higher Action", source, extra, true);

        }
        return true;
    }

    public boolean possibleflag(Player player, String key, String value, String source, String extra, String input) throws IOException { //Used for chat if word is contained with in another word
        reports.report(player.getName(), key, input, source, value);// adds report
        addMute.addwatch(player);// adds tp watch list
        if (!value.contains("Blacklisted Term")) {
            ChatFilter.getInstance().getLogger().info( "[Chat Filter]: [Trigger]"+key+" :" + player.getName() + " Has attempted to say "+value);
            notfiy.staff(player, input, key, value, source, extra, false);

        }else {
            ChatFilter.getInstance().getLogger().info( "[Chat Filter]:" + player.getName() + " Has attempted to say a blacklistedTerm");
            notfiy.staff(player, "Message removed for security", "BlackListed Term", "Higher Action", source, extra, false);

        }

        return true;
    }

    private String removeInvalids(String input) {
        String mString;
        mString = input.replace("?", "");
        mString = mString.replace(".", " "); //To avoid merging messages together its a space
        mString = mString.replace(",", " ");

        return mString;
    }
}



