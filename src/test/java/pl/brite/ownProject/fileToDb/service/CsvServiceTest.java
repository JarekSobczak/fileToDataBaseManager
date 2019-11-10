package pl.brite.ownProject.fileToDb.service;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.brite.ownProject.fileToDb.model.Customer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvServiceTest {

    List<Customer> customers =new ArrayList<>();
    File file;
    @Mock CsvService csvService=new CsvService(customers);

    @BeforeEach
    void setUp() {
        file = new File("fileForTest.txt");
    }
    @Test
    void havingFullDatasFromCsvFileMapsToCustomerObjectAndAddToList() {
        String content="name,surname,age,city,contacts.contact.0.contact_data,contacts.contact.0.type,contacts.contact.1.contact_data,contacts.contact.1.type\n" +
                "Jan,Kowalski,24,Lublin,jkowal@gmail.com,1,777222333,2\n";

        try {
            FileUtils.writeStringToFile(file,content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvService.mapCsv(file);

        Assert.assertTrue(customers.get(0).getAge().equalsIgnoreCase("24"));
        Assert.assertTrue(customers.get(0).getCity().equalsIgnoreCase("Lublin"));
        Assert.assertTrue(customers.get(0).getName().equalsIgnoreCase("Jan"));
        Assert.assertTrue(customers.get(0).getSurname().equalsIgnoreCase("Kowalski"));
        Assert.assertTrue(customers.get(0).getContacts().get(0).getContact_data().equalsIgnoreCase("jkowal@gmail.com"));
        Assert.assertTrue(customers.get(0).getContacts().get(1).getContact_data().equalsIgnoreCase("777222333"));
        Assert.assertEquals(1, customers.get(0).getContacts().get(0).getType());
        Assert.assertEquals(2, customers.get(0).getContacts().get(1).getType());
    }
    @Test
    void havingDatasFromCsvFileWithoutContactsMapsToCustomerObjectAndAddsToList(){
        String content= "name,surname,age,city\n" +
                "Jan,Kowalski,24,Lublin\n";
        try {
            FileUtils.writeStringToFile(file,content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvService.mapCsv(file);
        Assert.assertTrue(customers.get(0).getAge().equalsIgnoreCase("24"));
        Assert.assertTrue(customers.get(0).getCity().equalsIgnoreCase("Lublin"));
        Assert.assertTrue(customers.get(0).getName().equalsIgnoreCase("Jan"));
        Assert.assertTrue(customers.get(0).getSurname().equalsIgnoreCase("Kowalski"));
        Assert.assertTrue(customers.get(0).getContacts().isEmpty());
    }



    @AfterEach
    void tearDown() {
        file.delete();
        customers.clear();
    }
}
