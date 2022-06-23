package kineticnetwork.net.chatfilter.Listeners;


import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.MuteHandler.CheckMute;
import kineticnetwork.net.chatfilter.Util.TextCheck;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;

public class Chat implements Listener {
    CheckMute checkmute = new CheckMute();

    @EventHandler
    public void OnChat(final AsyncPlayerChatEvent event) throws IOException {
        String input = event.getMessage().toLowerCase();
        final Player player = event.getPlayer();

        boolean muted = checkmute.CheckMute(player);
        if (muted == true) {
            event.setCancelled(true);
            return;
        }

        TextCheck check = new TextCheck();
        if (check.checkmessage(input, player, "Chat", "null") == true) {
            if (ChatFilter.MuteMethod == 2) {
                event.getRecipients().clear();
                event.getRecipients().add(player);
                return;
            }
            event.setCancelled(true);// cancels message
        }

    }
}

