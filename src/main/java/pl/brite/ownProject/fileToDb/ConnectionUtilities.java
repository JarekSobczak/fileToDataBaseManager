package pl.brite.ownProject.fileToDb;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtilities {

    public static void close(Connection conn) {
        if (conn == null) return;
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        if (conn == null) return;
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
