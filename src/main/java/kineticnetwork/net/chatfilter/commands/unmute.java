package kineticnetwork.net.chatfilter.commands;

import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.MuteHandler.UnMute;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class unmute {

    public void unmute(String suspect, Player sender) {
        UnMute unmute = new UnMute();
        try {
            unmute.unMute(suspect, sender);
        } catch (IOException e) {
            sender.sendMessage(ChatColor.RED + "No record of this player found.");
        }
    }

    public void unmutec(String suspect) {
        UnMute unmute = new UnMute();
        try {
            unmute.unmutec(suspect);
        } catch (IOException e) {
            ChatFilter.getInstance().getLogger().info((ChatColor.RED + "No record of this player found."));

        }
    }
}
