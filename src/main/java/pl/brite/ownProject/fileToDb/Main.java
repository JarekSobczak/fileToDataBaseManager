package pl.brite.ownProject.fileToDb;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pl.brite.ownProject.fileToDb.model.Contact;
import pl.brite.ownProject.fileToDb.model.Customer;
import pl.brite.ownProject.fileToDb.repository.ContactRepository;
import pl.brite.ownProject.fileToDb.repository.CustomerRepository;
import pl.brite.ownProject.fileToDb.repository.TableRepository;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter data file path ...");
        String filePath = sc.nextLine();

        File file = new File(filePath);

        XmlMapper om = new XmlMapper();


        Connection conn = null;
        try {
            List<Customer> customers =new ArrayList<>();
                   customers.add(om.readValue(file,  Customer.class));
            conn = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);

            TableRepository tableRepo = new TableRepository(conn);
            CustomerRepository customerRepo = new CustomerRepository(conn);
            ContactRepository contactRepo = new ContactRepository(conn);

            conn.setAutoCommit(false);

            tableRepo.createTables();

            for (Customer customer : customers) {
                Integer id = customerRepo.save(customer);

                for (Contact contact : customer.getContacts()) {
                  if (contact!=null)contactRepo.save(contact, id);
                }
            }
            conn.commit();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            ConnectionUtilities.rollback(conn);
        } finally {
            ConnectionUtilities.close(conn);
        }
    }
}
