package kineticnetwork.net.chatfilter;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import kineticnetwork.net.chatfilter.DataBase.Connections;
import kineticnetwork.net.chatfilter.DataBase.DataBaseSetup;
import kineticnetwork.net.chatfilter.Listeners.Chat;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import sun.security.krb5.internal.ccache.CredentialsCache;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class config {
    private  FileConfiguration config;
    public void Config() {
        config= ChatFilter.getInstance().getConfig();
        try {
            final String[] list = {"noot, I dont like this", "bob, Its tasty", "john,You can guess"};
            final String url = "Noot.xyz";


        //  config.addDefault("Database", "database");
            config.createSection("Database");
            config.addDefault("Database.IP","localhost");
            config.addDefault("Database.Port","0000");
            config.addDefault("Database.Username","root");
            config.addDefault("Database.Password","root");
            config.addDefault("Database.Enabled",false);

            config.options().header("Blocked words can not contain capitals. The following format must be followed \n Word,Reason eg'penguin, he have flipper' ");
            config.addDefault("Blocked words", (Object) list);
            config.addDefault("url", url);
            config.options().copyDefaults(true);
            ChatFilter.instance.saveConfig();
        } catch (Exception e) {
            // some reason idk why this errors on frist time run
            ChatFilter.instance.getLogger().info("Ah i see this is first time run. Please use /chatfilter reload in game");
        }
    }
    public void loadConfig(){
        Connections con=new Connections();
        List<String> input = (List<String>) config.getList("Blocked words");
        MysqlDataSource dataSource = new MysqlConnectionPoolDataSource();

        try {
            for (int i = 0; i < input.size(); i++) {//Take config split by , addinto list
                String[] split = input.get(i).split(",");
             //   Blacklisted.put(split[0], split[1]);
            }
        } catch (Exception e) {
            ChatFilter.getInstance().getLogger().info("Incorrect format for config followed!");
        }

        ChatFilter.getInstance().getLogger().info(String.valueOf(config.getBoolean("Database.Enabled")));
        if (config.getBoolean("Database.Enabled")) {
           dataSource.setDatabaseName("Chatfilter");
           dataSource.setServerName((String) config.get("Database.IP"));
           dataSource.setPortNumber((Integer) config.get("Database.Port"));
           dataSource.setUser((String) config.get("Database.Username"));
           dataSource.setPassword((String) config.get("Database.Password"));
           con.connect(dataSource);
        }
    }
}
