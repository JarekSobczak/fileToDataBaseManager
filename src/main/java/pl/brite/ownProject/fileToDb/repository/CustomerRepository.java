package pl.brite.ownProject.fileToDb.repository;

import pl.brite.ownProject.fileToDb.Constants;
import pl.brite.ownProject.fileToDb.model.Customer;

import java.sql.*;

public class CustomerRepository {

    private Connection conn;

    public CustomerRepository(Connection conn) {
        this.conn = conn;
    }

    public Integer save(Customer customer) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Constants.INSERT_INTO_CUSTOMER_SQL, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, customer.getName());
        ps.setString(2, customer.getSurname());
        ps.setInt(3, customer.getAge());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);
        throw new SQLDataException();
    }
}
