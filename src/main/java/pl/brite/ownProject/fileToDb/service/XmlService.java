package pl.brite.ownProject.fileToDb.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pl.brite.ownProject.fileToDb.model.Customer;

import java.io.*;
import java.util.List;

class XmlService {

    private List<Customer> customers;
    private File file;

    XmlService(List<Customer> customers, File file) {
        this.customers = customers;
        this.file = file;
    }

    void mapXml() {
        XmlMapper xmlm = new XmlMapper();
        try {
            customers.add(xmlm.readValue(file, Customer.class));
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
