package kineticnetwork.net.chatfilter.DataBase;

import kineticnetwork.net.chatfilter.ChatFilter;
import kineticnetwork.net.chatfilter.Listeners.Chat;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.stream.Collectors;



import static kineticnetwork.net.chatfilter.ChatFilter.dataSource;

public class DataBaseSetup {
    public void DataBaseSetup() throws SQLException, IOException {

        String[] querySetup = new String[2];
        querySetup[0]="Use chatfilter ;";
        querySetup[1]="CREATE TABLE IF NOT EXISTS `chatfilter` (\n" +
                "  `UUID` int(11) NOT NULL,\n" +
                "  `Muted` tinyint(4) DEFAULT NULL,\n" +
                "  `Flaged` tinyint(4) DEFAULT NULL,\n" +
                "  `Infractions` multilinestring DEFAULT NULL,\n" +
                "  PRIMARY KEY (`UUID`)\n" +
                ")";


            // Mariadb can only handle a single query per statement. We need to split at ;.

            // execute each query to the database.
            for (int i=0; i<querySetup.length;i++) {
                // If you use the legacy way you have to check for empty queries here.
                try (Connection conn = dataSource.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(querySetup[i])) {
                    stmt.execute();
                }
            }
            ChatFilter.instance.getLogger().info("§2Database setup complete.");

    }
    }

