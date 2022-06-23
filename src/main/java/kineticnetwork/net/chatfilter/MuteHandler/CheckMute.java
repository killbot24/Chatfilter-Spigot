package kineticnetwork.net.chatfilter.MuteHandler;

import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.FileEditor.Reports;
import kineticnetwork.net.chatfilter.Notfiy;
import org.bukkit.entity.Player;

public class CheckMute {
    public boolean CheckMute(Player player) {
        Notfiy notfiy = new Notfiy();
        Reports reson = new Reports();
        try {
            for (int b = 0; b < ChatFilter.Mutes.length; ++b) {   // checks mutes
                if (player.getName().equals(ChatFilter.Mutes[b])) {
                    notfiy.sendresponse(player, reson.getword(player));
                    return true;
                }
            }
        } catch (Exception e) {
            ChatFilter.getInstance().getLogger().info("[Chat-filter]: Error in reading mutes " + e.getMessage());
        }
        return false;
    }
}
