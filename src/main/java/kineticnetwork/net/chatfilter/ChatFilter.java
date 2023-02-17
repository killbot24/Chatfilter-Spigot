package kineticnetwork.net.chatfilter;

import kineticnetwork.net.chatfilter.DataBase.DataBaseSetup;
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

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ChatFilter extends JavaPlugin {
    public static ChatFilter instance;
   // public static FileConfiguration config;
    public static String[] Mutes;//Active mutes
    public static String URL;
    public static Map<String, String> Blacklisted = new HashMap<String, String>();
    public static String[] Watch;//Active watch
    //DataBase vars.
    public static boolean UseDatabase;
  //  public static String username=""; // Enter in your db username
  //  public static String Ip=""; // Enter in your db ip
  //  public static String database=""; // Enter in your db name
  //  public static String password=""; // Enter your password for the db
    public static String url = "jdbc:mysql://localhost/chatfilter"; // Enter URL with db name
    //Connection vars
    public static Connection connection; //This is the variable we will use to connect to database

    public static ChatFilter getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        config config=new config();
        config.Config();
        config.loadConfig();
       // List<String> input = (List<String>) this.getConfig().getList("Blocked words");
        /*
        username = (String) this.getConfig().get("Username");
        password = (String) this.getConfig().get("Password");
        Ip = (String) this.getConfig().get("IP");
        database = (String) this.getConfig().get("Database");
        UseDatabase = this.getConfig().getBoolean("Enabled");
        if (UseDatabase){
            this.getLogger().info("Loading databases");
            DataBaseSetup db=new DataBaseSetup();
            db.DataBaseSetup();
        }
        */
      /*  try {
            for (int i = 0; i < input.size(); i++) {//Take config split by , addinto list
                String[] split = input.get(i).split(",");
                Blacklisted.put(split[0], split[1]);
            }
        } catch (Exception e) {
            this.getLogger().info("Incorrect format for config followed!");
        }
*/


        // Url
        URL = getInstance().getConfig().getString("url");
       // MuteMethod = ChatFilter.getInstance().getConfig().getInt("Mute Method");

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
        URL = getInstance().getConfig().getString("url");
    }
}
