package pl.brite.ownProject.fileToDb.repository;

import pl.brite.ownProject.fileToDb.Constants;

import java.sql.Connection;
import java.sql.SQLException;

public class TableRepository {

    private Connection conn;

    public TableRepository(Connection conn) {
        this.conn = conn;
    }

    public void createTables() throws SQLException {
        conn.createStatement().execute(Constants.CREATE_CUSTOMER_TABLE);
        conn.createStatement().execute(Constants.CREATE_CONTACT_TABLE);
    }
}
