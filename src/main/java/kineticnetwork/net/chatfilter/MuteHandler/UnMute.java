package kineticnetwork.net.chatfilter.MuteHandler;

import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.FileEditor.Reports;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class UnMute {
    ReadMute readmute = new ReadMute();
    Reports reports = new Reports();

    public void unMute(final String player, final Player sender) throws IOException {   //unmutes player
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath(), "Active-mutes.yml");
        final PrintWriter out = new PrintWriter(new FileWriter(file, true));
        final Scanner myReader = new Scanner(file);
        final FileWriter fw = new FileWriter(file);
        final BufferedWriter bw = new BufferedWriter(fw);
        try {
            final List<?> inputa = Files.readAllLines(file.toPath());
            inputa.remove(player);
            for (int i = 0; i < inputa.size(); ++i) {
                bw.write(inputa.get(i).toString());
            }
        } catch (Exception e) {
            ChatFilter.getInstance().getLogger().info("[Warning] Issue editing file in unmute");
        }
        sender.sendMessage(ChatColor.GOLD + " " + player + ChatColor.DARK_GREEN + " is unmuted");
        ChatFilter.getInstance().getLogger().info(ChatColor.GOLD + " " + player + ChatColor.DARK_GREEN + " is unmuted");
        readmute.Readmute();
        reports.addlog(sender.getName(), player);
    }

    public void unmutec(final String suspect) throws IOException {
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath(), "Active-mutes.yml");
        final FileWriter fw = new FileWriter(file);
        final BufferedWriter bw = new BufferedWriter(fw);
        try {
            final List<?> inputa = Files.readAllLines(file.toPath());
            inputa.remove(suspect);
            for (int i = 0; i < inputa.size(); ++i) {
                bw.write(inputa.get(i).toString());
            }
        } catch (Exception e) {
            ChatFilter.getInstance().getLogger().info("[Warning] Issue editing file in unmute");
        }
        ChatFilter.getInstance().getLogger().info(ChatColor.GOLD + " " + suspect + ChatColor.DARK_GREEN + " is unmuted");
        readmute.Readmute();
    }

    public void unwatch(final String player, final Player sender) throws IOException {   //unmutes player
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath(), "ActiveWatch.yml");
        final PrintWriter out = new PrintWriter(new FileWriter(file, true));
        final Scanner myReader = new Scanner(file);
        final FileWriter fw = new FileWriter(file);
        final BufferedWriter bw = new BufferedWriter(fw);
        try {
            final List<?> inputa = Files.readAllLines(file.toPath());
            inputa.remove(player);
            for (int i = 0; i < inputa.size(); ++i) {
                bw.write(inputa.get(i).toString());
            }
        } catch (Exception e) {
            ChatFilter.getInstance().getLogger().info("[Warning] Issue editing file in unFlaged");
        }
        sender.sendMessage(ChatColor.GOLD + " " + player + ChatColor.DARK_GREEN + " is unFlaged");//Todo this needs a better name
        ChatFilter.getInstance().getLogger().info(ChatColor.GOLD + " " + player + ChatColor.DARK_GREEN + " is unFlaged");
        readmute.Readcheck();
        reports.addlog(sender.getName(), player);
    }

    public void uwatchc(final String suspect) throws IOException {
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath(), "ActiveWatch.yml");
        final FileWriter fw = new FileWriter(file);
        final BufferedWriter bw = new BufferedWriter(fw);
        try {
            final List<?> inputa = Files.readAllLines(file.toPath());
            inputa.remove(suspect);
            for (int i = 0; i < inputa.size(); ++i) {
                bw.write(inputa.get(i).toString());
            }
        } catch (Exception e) {
            ChatFilter.getInstance().getLogger().info("[Warning] Issue editing file in unFlaged");
        }
        ChatFilter.getInstance().getLogger().info(ChatColor.GOLD + " " + suspect + ChatColor.DARK_GREEN + " is unFlaged");
        readmute.Readmute();
    }
}
