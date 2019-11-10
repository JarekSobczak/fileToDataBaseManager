package pl.brite.ownProject.fileToDb.service;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.brite.ownProject.fileToDb.model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class XmlServiceTest {

    List<Customer>customers =new ArrayList<>();
    File file;

   @Mock XmlService xmlService=new XmlService(customers);

   @BeforeEach
   void setUp(){
       file = new File("fileForTest.xml");
   }

    @Test
    void havingFullDatasFromXmlFileMapsToCustomerObjectAndAddsToList() {

                String content= "<customer>\n" +
                "    <age>24</age>\n" +
                "    <city>Lublin</city>\n" +
                "    <contacts>\n" +
                "        <contact>\n" +
                "            <contact_data>jkowal@gmail.com</contact_data>\n" +
                "            <type>1</type>\n" +
                "        </contact>\n" +
                "        <contact>\n" +
                "            <contact_data>777222333</contact_data>\n" +
                "            <type>2</type>\n" +
                "        </contact>\n" +
                "    </contacts>\n" +
                "    <name>Jan</name>\n" +
                "    <surname>Kowalski</surname>\n" +
                "</customer>";

        try {
            FileUtils.writeStringToFile(file,content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        xmlService.mapXml(file);
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
    void havingDatasFromXmlFileWithoutContactsMapsToCustomerObjectAndAddsToList() {

        String content= "<customer>\n" +
                "    <age>24</age>\n" +
                "    <city>Lublin</city>\n" +
                "    <name>Jan</name>\n" +
                "    <surname>Kowalski</surname>\n" +
                "</customer>";
        try {
            FileUtils.writeStringToFile(file,content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        xmlService.mapXml(file);
        Assert.assertTrue(customers.get(0).getAge().equalsIgnoreCase("24"));
        Assert.assertTrue(customers.get(0).getCity().equalsIgnoreCase("Lublin"));
        Assert.assertTrue(customers.get(0).getName().equalsIgnoreCase("Jan"));
        Assert.assertTrue(customers.get(0).getSurname().equalsIgnoreCase("Kowalski"));
        Assert.assertNull(customers.get(0).getContacts());
    }

    @AfterEach
    void tearDown(){
        file.delete();
        customers.clear();
    }
}
