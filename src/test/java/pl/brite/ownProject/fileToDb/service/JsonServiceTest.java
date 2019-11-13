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

class JsonServiceTest {

    List<Customer> customers =new ArrayList<>();
    File file;

    @Mock
    JsonService jsonService=new JsonService(customers);

    @BeforeEach
    void setUp(){
        file = new File("fileForTest.json");
    }
    @Test
    void havingFullDatasFromJsonFileMapsToCustomerObjectAndAddsToList() {
        String content="[\n" +
                "{\n" +
                "  \"name\": \"Anna\",\n" +
                "  \"surname\": \"Grudzińska\",\n" +
                "  \"age\": \"11\",\n" +
                "  \"city\": \"Biała Podlaska\",\n" +
                "  \"contacts\": [\n" +
                "    {\n" +
                "      \"contact_data\": \"annaGrudzinska@gmail.com\",\n" +
                "      \"type\": \"1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"contact_data\": \"667994332\",\n" +
                "      \"type\": \"2\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n"+
                "]";

        try {
            FileUtils.writeStringToFile(file,content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonService.mapJson(file);
        Assert.assertTrue(customers.get(0).getAge().equalsIgnoreCase("11"));
        Assert.assertTrue(customers.get(0).getCity().equalsIgnoreCase("Biała Podlaska"));
        Assert.assertTrue(customers.get(0).getName().equalsIgnoreCase("Anna"));
        Assert.assertTrue(customers.get(0).getSurname().equalsIgnoreCase("Grudzińska"));
        Assert.assertTrue(customers.get(0).getContacts().get(0).getContact_data().equalsIgnoreCase("annaGrudzinska@gmail.com"));
        Assert.assertTrue(customers.get(0).getContacts().get(1).getContact_data().equalsIgnoreCase("667994332"));
        Assert.assertEquals(1, customers.get(0).getContacts().get(0).getType());
        Assert.assertEquals(2, customers.get(0).getContacts().get(1).getType());
    }

    @Test
    void havingDatasFromJsonFileWithoutContactsMapsToCustomerObjectAndAddsToList() {

        String content= "[\n" +
                "{\n" +
                "  \"name\": \"Anna\",\n" +
                "  \"surname\": \"Grudzińska\",\n" +
                "  \"age\": \"11\",\n" +
                "  \"city\": \"Biała Podlaska\"\n"+
                "}\n"+
                "]";
        try {
            FileUtils.writeStringToFile(file,content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonService.mapJson(file);
        Assert.assertTrue(customers.get(0).getAge().equalsIgnoreCase("11"));
        Assert.assertTrue(customers.get(0).getCity().equalsIgnoreCase("Biała Podlaska"));
        Assert.assertTrue(customers.get(0).getName().equalsIgnoreCase("Anna"));
        Assert.assertTrue(customers.get(0).getSurname().equalsIgnoreCase("Grudzińska"));
        Assert.assertNull(customers.get(0).getContacts());
    }

    @AfterEach
    void tearDown() {
        file.delete();
        customers.clear();
    }
}
