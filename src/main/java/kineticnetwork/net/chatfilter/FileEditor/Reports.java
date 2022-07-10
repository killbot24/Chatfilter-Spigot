package kineticnetwork.net.chatfilter.FileEditor;

import kineticnetwork.net.chatfilter.ChatFilter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.file.Files;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Reports {
    public void report(final String player, final String trigger, final String warning, final String Type, String reason) throws IOException { // adds a report in file
        createfolder();
        // ChatFilter.getInstance().getLogger().info("player "+player);
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsoluteFile() + "/Warnings", player + ".yml");
        final PrintWriter out = new PrintWriter(new FileWriter(file, true));
       // final String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy||HH:mm ").format(new java.util.Date());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                ChatFilter.getInstance().getLogger().severe(player + "'s data file could not be created!");
                e1.printStackTrace();
            }
        } else {
            try {
                out.write("\n" + timeStamp + ",[Trigger word]:" + trigger + ",[Warning type]: " + Type + ",[Message]: " + warning + ",[Reason]: " + reason);
                out.close();
            } catch (Exception e2) {
                ChatFilter.getInstance().getLogger().info("[Warning] Issue writing file");
            }
        }
    }

    public void readreport(final String suspect, final Player sender) throws IOException {
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath() + "/Warnings", suspect + ".yml");
        final Scanner myReader = new Scanner(file);
        try {
            final List<?> infraction = Files.readAllLines(file.toPath());
            String[] user = new String[infraction.size()];
            user = infraction.toArray(user);
            sender.sendMessage(ChatColor.GOLD + "Player's infraction \n");
            for (int i = 0; i < user.length; ++i) {
                sender.sendMessage("\n"+ChatColor.GRAY + user[i]);
            }
            myReader.close();

        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "No record of player");
        }
    }

    public String getword(final Player player) throws IOException {//Gets word for checkmute reason
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath() + "/Warnings", player.getName() + ".yml");
        final Scanner myReader = new Scanner(file);
        String Reason = "null";
        try { //Todo not getting anything
            List<?> infraction = Files.readAllLines(file.toPath());
            String[] user = new String[infraction.size()];
            user = infraction.toArray(user);
            int lenght = user.length;
            String[] split = user[lenght - 1].split(",");
            Reason = split[split.length - 1];
            Reason = Reason.replace("[Reason]:", "");
            myReader.close();

        } catch (Exception e) {
            e.getMessage();
        }
        return Reason;
    }

    public void createfolder() {
        final File warningfolder = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath(), "Warnings");
        if (!warningfolder.exists()) {
            warningfolder.mkdir();
        }
    }

    public void addlog(String player, String mute) throws IOException { // logs all un mutes
        createfolder();
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsoluteFile(), "log.yml");
        final PrintWriter out = new PrintWriter(new FileWriter(file, true));
        final String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                ChatFilter.getInstance().getLogger().severe("[Chat-filter] Log updated");
                e1.printStackTrace();
            }
        } else {
            try {
                out.write("\n" + timeStamp + " " + player + " has unmute " + mute);
                out.close();
            } catch (Exception e2) {
                ChatFilter.getInstance().getLogger().info("[Warning] Issue writing file");
            }
        }
    }

    public void readreportc(final String suspect) throws FileNotFoundException {
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath() + "/Warnings", suspect + ".yml");
        final Scanner myReader = new Scanner(file);
        try {
            final List<?> infraction = Files.readAllLines(file.toPath());
            String[] user = new String[infraction.size()];
            user = infraction.toArray(user);
            ChatFilter.getInstance().getLogger().info(ChatColor.GOLD + "Player's infraction");
            for (int i = 0; i < user.length; ++i) {
                ChatFilter.getInstance().getLogger().info(ChatColor.GRAY + user[i]);
            }
            myReader.close();
        } catch (Exception e) {
            ChatFilter.getInstance().getLogger().info(ChatColor.RED + "No record of player");
        }
    }
}
