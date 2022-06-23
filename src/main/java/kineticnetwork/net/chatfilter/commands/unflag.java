package kineticnetwork.net.chatfilter.commands;

import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.MuteHandler.UnMute;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class unflag {
    public void unwatch(String suspect, Player sender) {
        UnMute unmute = new UnMute();
        try {
            unmute.unwatch(suspect, sender);
        } catch (IOException e) {
            sender.sendMessage(ChatColor.RED + "No record of this player found.");
        }
    }

    public void unwatchc(String suspect) {
        UnMute unmute = new UnMute();
        try {
            unmute.uwatchc(suspect);
        } catch (IOException e) {
            ChatFilter.getInstance().getLogger().info((ChatColor.RED + "No record of this player found."));

        }
    }
}
