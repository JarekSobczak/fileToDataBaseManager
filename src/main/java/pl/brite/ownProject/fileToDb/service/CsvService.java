package pl.brite.ownProject.fileToDb.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import pl.brite.ownProject.fileToDb.model.Contact;
import pl.brite.ownProject.fileToDb.model.Customer;

import java.io.*;
import java.util.*;

class CsvService {

    private List<Customer> customers;

    CsvService(List<Customer> customers) {
        this.customers = customers;
    }

    void mapCsv(File file) {
        List<Map<String, String>> response = new LinkedList<Map<String, String>>();

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> iterator = null;
        try {
            iterator = mapper.reader(Map.class)
                    .with(schema)
                    .readValues(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (iterator.hasNext()) {
            response.add(iterator.next());
        }

        String name = "";
        String surname = "";
        String age = "";
        String city = "";
        Map<String, Contact> userContacts = new HashMap<>();
        for (Map<String, String> fromCSV : response) {
            String contactPrefix = "contacts.contact.";
            name = fromCSV.get("name");
            surname = fromCSV.get("surname");
            age = fromCSV.get("age");
            city = fromCSV.get("city");

            for (Map.Entry<String, String> entry : fromCSV.entrySet()) {
                if (!entry.getKey().startsWith(contactPrefix)) {
                    continue;
                }
                String[] keyElements = entry.getKey().replaceAll(contactPrefix, "").split("\\.");
                String index = keyElements[0];
                String fieldName = keyElements[1];
                if (!userContacts.containsKey(index)) {
                    Contact contact = new Contact();
                    userContacts.put(index, contact);

                }
                userContacts.get(index).update(fieldName, entry.getValue());

            }
            Customer customer = new Customer(name, surname, age, city, new ArrayList<>(userContacts.values()));
            customers.add(customer);
            userContacts.clear();
        }

    }
}

