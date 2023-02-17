package kineticnetwork.net.chatfilter.DataBase;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import kineticnetwork.net.chatfilter.ChatFilter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    Connection connection;
    public Connection connect(MysqlDataSource dataSource){
       // String url = "jdbc:mysql://localhost/chatfilter"; // Enter URL with db name
        try (Connection conn = dataSource.getConnection()) {
            if (!conn.isValid(1)) {
                throw new SQLException("Could not establish database connection.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


     /*   if (url=="localhost")Url=url;
        try { // try catch to get any SQL errors (for example connections errors)
           connection = DriverManager.getConnection(Url, username, password);
            // with the method getConnection() from DriverManager, we're trying to set
            // the connection's url, username, password to the variables we made earlier and
            // trying to get a connection at the same time. JDBC allows us to do this.
        } catch (SQLException e) { // catching errors
            ChatFilter.getInstance().getLogger().info("Database connection failed");
            e.printStackTrace(); // prints out SQLException errors to the console (if any)
            return null;
        }
        return connection;
    }
    public boolean disconnect(){
        // invoke on disable.
        try { // using a try catch to catch connection errors (like wrong sql password...)
            if (ChatFilter.connection!=null && !ChatFilter.connection.isClosed()){ // checking if connection isn't null to
                // avoid receiving a nullpointer
                ChatFilter.connection.close(); // closing the connection field variable.
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

      */
        return null;
    }
}
