package kineticnetwork.net.chatfilter;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import kineticnetwork.net.chatfilter.DataBase.Connections;
import org.bukkit.configuration.file.FileConfiguration;
import static kineticnetwork.net.chatfilter.ChatFilter.dataSource;
import java.util.List;

public class config {
    private  FileConfiguration config;
    public void Config() {

        config= ChatFilter.getInstance().getConfig();
        try {
            String[] defaultBlocked = {"noot, I dont like this", "bob, Its tasty", "john,You can guess"};

           // ChatFilter.instance.saveDefaultConfig();
            config.options().header("Blocked words can not contain capitals. The following format must be followed \n Word,Reason eg'penguin, he have flipper' ");
        //  config.addDefault("Database", "database");
            config.createSection("Database");
            config.set("Database.IP","localhost");
            config.set("Database.Port","0000");
            config.set("Database.Username","root");
            config.set("Database.Password","root");
            config.set("Database.Enabled",false);

            config.set("Blocked words", (Object) defaultBlocked);
            config.set("url","Noot.xyz");
          //  config.addDefault("Blocked words", (Object) list);
         //   config.addDefault("url", url);
            config.options().copyDefaults(false);
           // ChatFilter.instance.saveConfig();

           // ChatFilter.instance.saveConfig();
        } catch (Exception e) {
            // some reason idk why this errors on frist time run
            ChatFilter.instance.getLogger().info("Ah i see this is first time run. Please use /chatfilter reload in game");
            reload();
        }
    }
    public void reload() {
        ChatFilter.getInstance().reloadConfig();
        loadConfig();
    }
    public void loadConfig(){
        config= ChatFilter.getInstance().getConfig();
        Connections con=new Connections();
        List<String> input = (List<String>) config.getList("Blocked words");

        MysqlDataSource database = new MysqlConnectionPoolDataSource();
        try {
            for (int i = 0; i < input.size(); i++) {//Take config split by , addinto list
                String[] split = input.get(i).split(",");
            ChatFilter.Blacklisted.put(split[0], split[1]);
            }
        } catch (Exception e) {
            ChatFilter.getInstance().getLogger().info("Incorrect format for config followed!");
        }

        ChatFilter.getInstance().getLogger().info(String.valueOf(config.getBoolean("Database.Enabled")));
        if (config.getBoolean("Database.Enabled")) {
            ChatFilter.databaseEnabled=true;
            database.setDatabaseName("Chatfilter");
            database.setServerName((String) config.get("Database.IP"));
            database.setPortNumber( Integer.parseInt( config.get("Database.Port").toString()));
            database.setUser((String) config.get("Database.Username"));
            database.setPassword((String) config.get("Database.Password"));
            dataSource=database;
            con.Connect(dataSource);
        }
    }
}
