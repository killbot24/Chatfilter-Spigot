package kineticnetwork.net.chatfilter.commands;

import kineticnetwork.net.chatfilter.ChatFilter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String Commanda = null;
        if (sender instanceof Player) {

            try {
                Commanda = args[0];


                if (Commanda.equalsIgnoreCase("list")) {
                    list listcommand = new list();
                    listcommand.listplayer((Player) sender);// lists active mutes to sender
                } else if (Commanda.equalsIgnoreCase("reload")) {
                    ChatFilter.reload();
                    sender.sendMessage("Chatfilter Reloaded");
                } else if (Commanda.equalsIgnoreCase("check")) {
                    check checkcommand = new check();
                    checkcommand.check(args[1], (Player) sender);
                } else if (Commanda.equalsIgnoreCase("unmute")) {
                    unmute unmutecommand = new unmute();
                    unmutecommand.unmute(args[1], ((Player) sender).getPlayer());
                } else if (Commanda.equalsIgnoreCase("unwatch")) {
                    unflag uw = new unflag();
                    uw.unwatch(args[1], ((Player) sender).getPlayer());
                }
            } catch (Exception e) {
                sender.sendMessage(ChatColor.GRAY + "The correct usage of these commands are\n /chatfilter list " + ChatColor.GRAY + "(" + ChatColor.GOLD + "This command shows the list of currently muted people" + ChatColor.GRAY + ")\n /chatfilter check <player> " + ChatColor.GRAY + "(" + ChatColor.GOLD + "This command checks the player for any infraction " + ChatColor.GRAY + ")\n /chatfilter unmute <player> " + ChatColor.GRAY + "(" + ChatColor.GOLD + "This command unmutes the named player" + ChatColor.GRAY + ")" + ChatColor.GRAY + ")\n /chatfilter accept" + ChatColor.GRAY + "(" + ChatColor.GOLD + "Using this command you acknowledge that you know where to go for help" + ChatColor.GRAY + "\n /chatfilter unflag" + ChatColor.GRAY + "(" + ChatColor.GOLD + "removes player from flaged" + ChatColor.GRAY);
                return true;
            }

        } else {
            try {
                Commanda = args[0];
                // sender is consle
                if (Commanda.equalsIgnoreCase("reload")) {
                    ChatFilter.reload();
                } else if (Commanda.equalsIgnoreCase("check")) {
                    check checkcommand = new check();
                    checkcommand.checkc(args[1]);
                } else if (Commanda.equalsIgnoreCase("list")) {
                    list listcommand = new list();
                    listcommand.listconsle();
                } else if (Commanda.equalsIgnoreCase("unmute")) {
                    unmute unmutecommand = new unmute();
                    unmutecommand.unmutec(args[1]);
                } else if (Commanda.equalsIgnoreCase("unwatch")) {
                    unflag uw = new unflag();
                    uw.unwatchc(args[1]);
                }


            } catch (Exception e) {
                ChatFilter.getInstance().getLogger().info((ChatColor.GRAY + "The correct usage of these commands are\n /chatfilter list " + ChatColor.GRAY + "(" + ChatColor.GOLD + "This command shows the list of currently muted people" + ChatColor.GRAY + ")\n /chatfilter check <player> " + ChatColor.GRAY + "(" + ChatColor.GOLD + "This command checks the player for any infraction " + ChatColor.GRAY + ")\n /chatfilter unmute <player> " + ChatColor.GRAY + "(" + ChatColor.GOLD + "This command unmutes the named player" + ChatColor.GRAY + ")" + ChatColor.GRAY + ")\n /chatfilter accept" + ChatColor.GRAY + "(" + ChatColor.GOLD + "Using this command you acknowledge that you know where to go for help" + ChatColor.GRAY));

            }
        }
        return true;
    }


}

