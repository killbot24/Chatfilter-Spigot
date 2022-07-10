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

        if (checkmute.CheckMute(player) == true) {
            event.setCancelled(true);
            return;
        }

        TextCheck check = new TextCheck();
        int checkmessage= check.checkmessage(input, player, "Chat", "null");
        if (checkmessage == 1||checkmessage==2) {
            if (checkmessage==1) {// 1 = possible flag, 2 confirmed flag
                player.sendMessage("Your message has been flagged by chatfilter.");
                event.getRecipients().clear();
                event.getRecipients().add(player);
                return;
            }else {
                event.setCancelled(true);// cancels message
            }
        }

    }
}

