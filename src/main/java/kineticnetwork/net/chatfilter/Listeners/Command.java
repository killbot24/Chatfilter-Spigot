package kineticnetwork.net.chatfilter.Listeners;

import kineticnetwork.net.chatfilter.Util.TextCheck;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.IOException;

public class Command implements Listener {

    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent event) throws IOException {
        Player player = event.getPlayer();
        String[] message = event.getMessage().split("\\s+");
        String fullmessage = "";

        if (message[0].equalsIgnoreCase("/msg") || message[0].equalsIgnoreCase("/whisper") || message[0].equalsIgnoreCase("/tell") || message[0].equalsIgnoreCase("/mail")) {
            fullmessage = event.getMessage();
            TextCheck check = new TextCheck();
            if (check.checkmessage(fullmessage, player, "Message", "null") == true) {
                event.setCancelled(true);// cancels message
            }
        }
    }


}

