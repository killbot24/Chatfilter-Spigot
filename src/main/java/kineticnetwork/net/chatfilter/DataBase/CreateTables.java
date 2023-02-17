package kineticnetwork.net.chatfilter.DataBase;

import kineticnetwork.net.chatfilter.ChatFilter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTables {
    public boolean createTables(){
       // String sql = "CREATE TABLE IF NOT EXISTS Muted(Something varchar(64));";
        String[] sql={"CREATE TABLE IF NOT EXISTS chatfilter (UUID int(11) NOT NULL,Muted tinyint(4) DEFAULT NULL,Flaged tinyint(4) DEFAULT NULL,Infractions multilinestring DEFAULT NULL,PRIMARY KEY (`UUID`)) ENGINE=MyISAM DEFAULT CHARSET=latin1;"};
        try {
            PreparedStatement stmt = ChatFilter.connection.prepareStatement(sql[0]);
            // use executeUpdate() to update the databases table.
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
