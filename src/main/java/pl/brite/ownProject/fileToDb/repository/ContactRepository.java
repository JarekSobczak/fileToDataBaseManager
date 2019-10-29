package pl.brite.ownProject.fileToDb.repository;

import pl.brite.ownProject.fileToDb.Constants;
import pl.brite.ownProject.fileToDb.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactRepository {

    private Connection conn;

    public ContactRepository(Connection conn) {
        this.conn = conn;
    }

    public void save(Contact contact, Integer idCustomer) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constants.INSERT_INTO_CONTACT_SQL);

        ps.setInt(1, idCustomer);
        ps.setInt(2, contact.getType());
        ps.setString(3, contact.getContact());

        ps.execute();
    }
}
