package kineticnetwork.net.chatfilter.commands;

import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Reload {
    public void reloadp() {
        ChatFilter.instance.reloadConfig();
        final List<?> input = ChatFilter.config.getList("Blacklisted words");
        ChatFilter.getInstance().getLogger().info(input + " list");

        // filter messages
        final List<?> inputa = ChatFilter.config.getList("Whitelisted");
        ChatFilter.Whitelist = new String[inputa.size()];
        ChatFilter.Whitelist = inputa.toArray(ChatFilter.Whitelist);

        // Mute time
        int muteTimer = ChatFilter.config.getInt("Mute");
        ChatFilter.muteTime = muteTimer;
        ChatFilter.getInstance().getLogger().info("Reload complete");

    }

}
