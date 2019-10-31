package pl.brite.ownProject.fileToDb.service;

import pl.brite.ownProject.fileToDb.ConnectionUtilities;
import pl.brite.ownProject.fileToDb.Constants;
import pl.brite.ownProject.fileToDb.model.Contact;
import pl.brite.ownProject.fileToDb.model.Customer;
import pl.brite.ownProject.fileToDb.repository.ContactRepository;
import pl.brite.ownProject.fileToDb.repository.CustomerRepository;
import pl.brite.ownProject.fileToDb.repository.TableRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

class DbService {


    void writeToDB(List<Customer> customers) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
            conn.setAutoCommit(false);
            TableRepository tableRepo = new TableRepository(conn);
            CustomerRepository customerRepo = new CustomerRepository(conn);
            ContactRepository contactRepo = new ContactRepository(conn);
            tableRepo.createTables();

            for (Customer customer : customers) {
                Integer id = customerRepo.save(customer);
                if (customer.getContacts() != null) {
                    for (Contact contact : customer.getContacts()) {
                        if (contact != null) contactRepo.save(contact, id);
                    }
                }
            }
            conn.commit();
        } catch (
                SQLException e) {
            e.printStackTrace();
            ConnectionUtilities.rollback(conn);
        } finally {
            ConnectionUtilities.close(conn);
        }
    }
}
