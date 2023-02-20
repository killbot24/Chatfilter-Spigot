package kineticnetwork.net.chatfilter;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import kineticnetwork.net.chatfilter.DataBase.Connections;
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ChatFilter extends JavaPlugin {
    public static ChatFilter instance;
    public static String[] Mutes;//Active mutes
    public static String URL;
    public static boolean databaseEnabled;
    public static Map<String, String> Blacklisted = new HashMap<String, String>();
    public static String[] Watch;//Active watch
    //DataBase vars.
    public static MysqlDataSource dataSource = new MysqlConnectionPoolDataSource();

    public static ChatFilter getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;
        config config=new config();
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        config.loadConfig();
        if (databaseEnabled){
            DataBaseSetup db=new DataBaseSetup();
            try {
                db.DataBaseSetup();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

}
