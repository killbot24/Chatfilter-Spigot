package kineticnetwork.net.chatfilter.commands;

import kineticnetwork.net.chatfilter.FileEditor.Reports;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class check {
    public void check(String suspect, Player sender) {
        Reports report = new Reports();
        try {
            report.readreport(suspect, sender);
        } catch (IOException e) {
            sender.sendMessage(ChatColor.RED + "No record of this player found.");
        }


    }

    public void checkc(String suspect) {
        Reports report = new Reports();
        try {
            report.readreportc(suspect);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
