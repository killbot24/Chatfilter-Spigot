package kineticnetwork.net.chatfilter.Listeners;

import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.MuteHandler.CheckMute;
import kineticnetwork.net.chatfilter.Notfiy;
import kineticnetwork.net.chatfilter.Util.TextCheck;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.io.IOException;

public class Sign implements Listener {
    Notfiy notfiy = new Notfiy();
    TextCheck check = new TextCheck();
    CheckMute mute = new CheckMute();

    @EventHandler
    public void onSignChange(final SignChangeEvent e) throws IOException {
        final Player player = e.getPlayer();
        boolean Mute = mute.CheckMute(player) == true;
        if (Mute == true) {
            e.setCancelled(true);
        } else {
            Location loc = e.getBlock().getLocation();

            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            String cords = "[World]:" + loc.getWorld().getName() + ", X:" + x + " Y:" + y + ", Z:" + z;
            final String[] line = e.getLines();
            String lines = "";
            for (int b2 = 0; b2 < line.length; ++b2) {
                lines += "/" + line[b2];

                int checkmessage = check.checkmessage(line[b2], player, "sign", cords);
                if (checkmessage != 0) {
                    e.setCancelled(true);
                    return;
                }
            }
            if (!lines.equalsIgnoreCase("////")) {
                ChatFilter.getInstance().getLogger().info("a"+lines+"a test");
                notfiy.sendsign(player, lines);
                ChatFilter.getInstance().getLogger().info("[Chat Filter]:" + player.getName() + " Has Placed a sign with message : New line defined by / :\n" + lines);
            }

        }
    }
}
