package kineticnetwork.net.chatfilter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class Notfiy {
    public void sendresponse(Player player, String reason) {
        player.sendMessage((ChatColor.DARK_RED + "You have been muted by the chat filter. Make a post on our forms." + ChatColor.GOLD + " Here is our forums " + ChatFilter.URL + "\n" + ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Reason" + ChatColor.DARK_GRAY + "]:" + ChatColor.WHITE + reason));

    }

    public void sendanvil(Player player, String reason) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission("cf.see")) {
                all.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Chat Filter" + ChatColor.DARK_GRAY + "]:" + ChatColor.GREEN + "[Anvil Alert]:" + player.getName() + " has named a item. " + ChatColor.WHITE + reason);


            }
        }
    }

    public void sendsign(Player player, String reason) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission("cf.see")) {
                all.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Chat Filter" + ChatColor.GRAY + "]:" + ChatColor.GREEN + "[Sign Alert]:" + player.getName() + " has placed a sign with message.  New line defined by / \n " + reason);


            }
        }
    }

    private String extra = "null";//Any extra things such as sign cords

    public void staff(Player player, String message, String trigger, String reason, String source, String extra, boolean confirmed) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission("cf.see")) {
                if (source == "Chat") {
                    if (confirmed == false) {
                        all.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "POSSIBLE FLAG" + ChatColor.DARK_GRAY + "]:\n" + ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Chat Filter" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_GRAY + player.getName() + ChatColor.RED + " Has attempted to say: " + ChatColor.GRAY + message + " " + ChatColor.DARK_GRAY + "\n[" + ChatColor.RED + "Trigger word" + ChatColor.DARK_GRAY + "]:" + ChatColor.WHITE + trigger + ChatColor.DARK_GRAY + "\n[" + ChatColor.RED + "Reason" + ChatColor.DARK_GRAY + "]:" + ChatColor.WHITE + reason);
                        getLogger().info("[Chat Filter]:" + player.getName() + " Has attempted to say " + message);
                    } else {
                        all.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Chat Filter" + ChatColor.GRAY + "]:" + player.getName() + ChatColor.RED + " Has attempted to say: " + ChatColor.GRAY + message + " " + ChatColor.DARK_GRAY + "\n[" + ChatColor.RED + "Trigger word" + ChatColor.DARK_GRAY + "]:" + ChatColor.WHITE + trigger + ChatColor.DARK_GRAY + "\n[" + ChatColor.RED + "Reason" + ChatColor.DARK_GRAY + "]:" + ChatColor.WHITE + reason);
                        getLogger().info("[Chat Filter]:" + player.getName() + " Has attempted to say " + message);
                    }
                } else if (source == "Anvil") {
                    all.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Chat Filter" + ChatColor.GRAY + "]:" + player.getName() + ChatColor.RED + " Has named a item called: " + ChatColor.GRAY + message + " " + ChatColor.DARK_GRAY + "\n[" + ChatColor.RED + "Trigger word" + ChatColor.DARK_GRAY + "]:" + ChatColor.WHITE + trigger + ChatColor.DARK_GRAY + "\n[" + ChatColor.RED + "Reason" + ChatColor.DARK_GRAY + "]:" + ChatColor.WHITE + reason);
                    getLogger().info("[Chat Filter]:" + player.getName() + " Has named a item called: " + message);
                } else if (source == "sign") {
                    all.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Chat Filter" + ChatColor.GRAY + "]:" + player.getName() + ChatColor.RED + " Has placed a sign: " + ChatColor.GRAY + message + " " + ChatColor.DARK_GRAY + "\n[" + ChatColor.RED + "Trigger word" + ChatColor.DARK_GRAY + "]:" + ChatColor.WHITE + trigger + ChatColor.DARK_GRAY + "\n[" + ChatColor.RED + "Reason" + ChatColor.DARK_GRAY + "]:" + ChatColor.WHITE + reason + ChatColor.DARK_GRAY + "\n[" + ChatColor.RED + "Cords" + ChatColor.DARK_GRAY + "]:" + ChatColor.WHITE + extra);
                    getLogger().info("[Chat Filter]:" + player.getName() + " Has attempted to place a sign with message " + message);
                }

            }
        }


    }
}
