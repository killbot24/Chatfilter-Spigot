package kineticnetwork.net.chatfilter.DataBase;

import kineticnetwork.net.chatfilter.ChatFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static kineticnetwork.net.chatfilter.ChatFilter.dataSource;

public class Querys {
    public void QuerySelector(String statement) throws SQLException {


            try (Connection conn = dataSource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(statement)) {
                stmt.execute();
            }
    }
}
