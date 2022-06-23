package kineticnetwork.net.chatfilter;

import kineticnetwork.net.chatfilter.Listeners.Anvil;
import kineticnetwork.net.chatfilter.Listeners.Chat;
import kineticnetwork.net.chatfilter.Listeners.Command;
import kineticnetwork.net.chatfilter.Listeners.Sign;
import kineticnetwork.net.chatfilter.MuteHandler.ReadMute;
import kineticnetwork.net.chatfilter.commands.Help;
import kineticnetwork.net.chatfilter.commands.Referancecommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ChatFilter extends JavaPlugin {
    public static ChatFilter instance;
    public static FileConfiguration config;
    public static String[] Mutes;//Active mutes
    public static String[] Whitelist; //Slated for removal
    public static Map<String, String> Blacklisted = new HashMap<String, String>();
    public static int muteTime;//Slated for removal
    public static String URL;
    public static int MuteMethod;
    public static String[] Watch;//Active watch

    public static String[] getMutes() {
        return Mutes;
    }

    public static void setMutes(String[] mutes) {
        Mutes = mutes;
    }


    public ChatFilter() {
        instance = this;
        this.config = this.getConfig();
    }

    public static ChatFilter getInstance() {

        return instance;
    }

    @Override
    public void onEnable() {
        this.getLogger().info("Loading config");
        config config = new config();
        config.Config();
        this.reloadConfig();
        List<String> input = (List<String>) this.getConfig().getList("Blocked words");
        getInstance().getLogger().info(input + " list");
        try {
            for (int i = 0; i < input.size(); i++) {//Take config split by , addinto list
                String[] split = input.get(i).split(",");
                //  this.getLogger().info(split.length+" "+split[0]);
                Blacklisted.put(split[0], split[1]);
            }
        } catch (Exception e) {
            this.getLogger().info("Incorrect format for config followed!");
        }

        // Url
        URL = getInstance().getConfig().getString("url");
        MuteMethod = ChatFilter.getInstance().getConfig().getInt("Mute Method");


        this.getLogger().info("Loading mutes");
        ReadMute readmute = new ReadMute();

        readmute.Readmute();
        readmute.Readcheck();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Chat(), this);
        pm.registerEvents(new Anvil(), this);
        pm.registerEvents(new Sign(), this);
        pm.registerEvents(new Command(), this);
        this.getCommand("chatfilter").setExecutor(new Help());
        this.getCommand("filter").setExecutor(new Referancecommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getScheduler().cancelTasks(this);
    }

    public static void reload() {
        ChatFilter.getInstance().reloadConfig();
        List<String> input = (List<String>) ChatFilter.getInstance().getConfig().getList("Blocked words");
        getInstance().getLogger().info(input + " list");
        for (int i = 0; i < input.size(); i++) {//Take config split by , addinto list
            String[] split = input.get(i).split(",");
            Blacklisted.put(split[0], split[1]);
        }
        MuteMethod = ChatFilter.getInstance().getConfig().getInt("Mute Method");
        URL = getInstance().getConfig().getString("url");
    }
}
