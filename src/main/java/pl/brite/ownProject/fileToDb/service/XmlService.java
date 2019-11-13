package pl.brite.ownProject.fileToDb.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pl.brite.ownProject.fileToDb.model.Customer;

import java.io.*;
import java.util.List;

class XmlService {

    private List<Customer> customers;


    XmlService(List<Customer> customers) {
        this.customers = customers;

    }

    void mapXml(File file) {
        XmlMapper xmlm = new XmlMapper();
        try {
            customers.addAll(xmlm.readValue(file, new com.fasterxml.jackson.core.type.TypeReference<List<Customer>>(){
            }));
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
