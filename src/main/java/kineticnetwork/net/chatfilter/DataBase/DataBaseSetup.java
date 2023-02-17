package kineticnetwork.net.chatfilter.DataBase;

import kineticnetwork.net.chatfilter.ChatFilter;

public class DataBaseSetup {
    public void DataBaseSetup(){
        Connections con=new Connections();
        CreateTables ct=new CreateTables();
        ChatFilter.getInstance().getLogger().info("Connected to database. Creating tables");
        if (ct.createTables()==false)return;
        ChatFilter.getInstance().getLogger().info("Database loaded.");
    }
}
