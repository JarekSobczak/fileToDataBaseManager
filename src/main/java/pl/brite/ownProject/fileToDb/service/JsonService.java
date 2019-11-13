package pl.brite.ownProject.fileToDb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.brite.ownProject.fileToDb.model.Customer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonService {

    private List<Customer> customers;

    public JsonService(List<Customer> customers) {
        this.customers = customers;
    }

    void mapJson(File file) {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        try {
            customers.addAll(om.readValue(file, new com.fasterxml.jackson.core.type.TypeReference<List<Customer>>() {
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
