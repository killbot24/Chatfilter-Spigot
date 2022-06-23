package kineticnetwork.net.chatfilter.Listeners;

import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.FileEditor.Reports;
import kineticnetwork.net.chatfilter.MuteHandler.AddMute;
import kineticnetwork.net.chatfilter.Notfiy;
import kineticnetwork.net.chatfilter.Util.TextCheck;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.InventoryView;

import java.io.IOException;

public class Anvil implements Listener {
    Notfiy notfiy = new Notfiy();
    TextCheck check = new TextCheck();

    @EventHandler
    public void onAnvil(final InventoryClickEvent e) throws IOException {

        final Player player = (Player) e.getWhoClicked();
        String tool = null;

        if (e.getInventory().getType() == InventoryType.ANVIL && e.getInventory().getItem(0) != null) {
            try {
                tool = e.getCurrentItem().getItemMeta().getDisplayName();

                if (tool == null) {
                    return;
                }
                if (check.checkmessage(e.getCurrentItem().getItemMeta().getDisplayName(), player, "Anvil", "null") == true) {
                    e.setCancelled(true);
                }
                if (e.getSlotType() == InventoryType.SlotType.RESULT && tool != e.getInventory().getItem(0).getItemMeta().getDisplayName()) {
                    notfiy.sendanvil(player, e.getCurrentItem().getItemMeta().getDisplayName());
                    ChatFilter.getInstance().getLogger().info("[Chat Filter]:" + player.getName() + " Has named a object called : " + e.getCurrentItem().getItemMeta().getDisplayName());
                }
            } catch (Exception ex) {
            }
        }
    }
}


