package kineticnetwork.net.chatfilter.MuteHandler;

import kineticnetwork.net.chatfilter.ChatFilter;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddMute {
    ReadMute readmute = new ReadMute();

    public void addMute(final Player player) throws IOException {   // mutes player
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath(), "Active-mutes.yml");
        final PrintWriter out = new PrintWriter(new FileWriter(file, false));
        try {
            out.write("\n" + player.getName() + "\n");
            out.close();
            readmute.Readmute();
        } catch (Exception e) {
            ChatFilter.getInstance().getLogger().info("[Warning] Issue writing file");
        }
        readmute.Readmute();
    }

    public void addwatch(final Player player) throws IOException {   // mutes player
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath(), "ActiveWatch.yml");
        final PrintWriter out = new PrintWriter(new FileWriter(file, false));
        try {
            out.write("\n" + player.getName() + "\n");
            out.close();
            readmute.Readcheck();
        } catch (Exception e) {
            ChatFilter.getInstance().getLogger().info("[Warning] Issue writing file");
        }
        readmute.Readcheck();
    }

}
