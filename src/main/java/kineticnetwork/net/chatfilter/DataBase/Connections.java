package kineticnetwork.net.chatfilter.DataBase;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import kineticnetwork.net.chatfilter.ChatFilter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    Connection connection;
    public Connection Connect(MysqlDataSource dataSource){
        try (Connection conn = dataSource.getConnection()) {
            if (!conn.isValid(1)) {
                throw new SQLException("Could not establish database connection.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
        public void closeConnection(MysqlDataSource dataSource) throws SQLException {
           dataSource.getConnection().close();
        }



}
