package kineticnetwork.net.chatfilter.MuteHandler;

import kineticnetwork.net.chatfilter.ChatFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class ReadMute {//Reads mutes from file

    public void Readmute() {
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath(), "Active-mutes.yml");
        Scanner myReader = null;
        try {
            file.createNewFile();
        } catch (IOException e3) {
            ChatFilter.getInstance().getLogger().severe("Error in createing active mute file");
        }
        try {
            myReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e2) {
                ChatFilter.getInstance().getLogger().severe("Error in createing active mute file");
                e2.printStackTrace();
            }
        } else {
            try {
                List<String> temp = Files.readAllLines(file.toPath());
                ChatFilter.Mutes = new String[temp.size()];
                ChatFilter.Mutes = temp.toArray(ChatFilter.Mutes);
            } catch (Exception b) {
                ChatFilter.getInstance().getLogger().info("[Chat-Filter] " + b.getStackTrace());
            }
        }
        myReader.close();
    }

    public void Readcheck() {
        final File file = new File(ChatFilter.getInstance().getDataFolder().getAbsolutePath(), "ActiveWatch.yml");
        Scanner myReader = null;
        try {
            file.createNewFile();
        } catch (IOException e3) {
            ChatFilter.getInstance().getLogger().severe("Error in creating active ActiveWatch file");
        }
        try {
            myReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e2) {
                ChatFilter.getInstance().getLogger().severe("Error in creating active ActiveWatch file");
                e2.printStackTrace();
            }
        } else {
            try {
                List<String> temp = Files.readAllLines(file.toPath());
                ChatFilter.Watch = new String[temp.size()];
                ChatFilter.Watch = temp.toArray(ChatFilter.Watch);
            } catch (Exception b) {
                ChatFilter.getInstance().getLogger().info("[Chat-Filter] " + b.getStackTrace());
            }
        }
        myReader.close();
    }
}
