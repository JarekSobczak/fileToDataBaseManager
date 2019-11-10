package pl.brite.ownProject.fileToDb.service;

import pl.brite.ownProject.fileToDb.model.Customer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {

    private List<Customer> customers = new ArrayList<>();
    private XmlService xmlService = new XmlService(customers);
    private CsvService csvService = new CsvService(customers);
    private DbService dbService = new DbService();

    public void writeFromFileToDb() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter data file path ...");
        String filePath = sc.nextLine();
        File file = new File(filePath);
        String type = filePath.trim().substring(filePath.length() - 3, filePath.length());



        if (type.equals("xml")) {
            xmlService.mapXml(file);

        }
        if (type.equals("txt")) {
            csvService.mapCsv(file);
        }
        dbService.writeToDB(customers);
    }
}

