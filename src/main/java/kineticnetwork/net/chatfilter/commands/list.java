package kineticnetwork.net.chatfilter.commands;

import kineticnetwork.net.chatfilter.ChatFilter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class list {

    public void listplayer(Player sender) {
        sender.sendMessage(ChatColor.RED + "Muted players\n" + Arrays.asList(ChatFilter.Mutes) + "\n" + ChatColor.RED + "Flaged players\n" + Arrays.asList(ChatFilter.Watch));
    }

    public void listconsle() {
        ChatFilter.getInstance().getLogger().info((ChatColor.RED + "Muted players\n" + Arrays.asList(ChatFilter.Mutes) + "\n " + ChatColor.RED + "Flaged players\n" + Arrays.asList(ChatFilter.Watch)));

    }
}
