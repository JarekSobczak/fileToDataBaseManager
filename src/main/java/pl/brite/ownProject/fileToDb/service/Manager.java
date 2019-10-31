package pl.brite.ownProject.fileToDb.service;

import pl.brite.ownProject.fileToDb.model.Customer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {

    public void writeFromFileToDb() {

        Scanner sc = new Scanner(System.in);
        List<Customer> customers = new ArrayList<>();
        System.out.println("Enter data file path ...");
        String filePath = sc.nextLine();
        File file = new File(filePath);
        String type = filePath.trim().substring(filePath.length() - 3, filePath.length());

        XmlService xmlService = new XmlService(customers, file);
        CsvService csvService = new CsvService(customers, file);
        DbService dbService = new DbService();

        if (type.equals("xml")) {
            xmlService.mapXml();

        }
        if (type.equals("txt")) {
            csvService.mapCsv();
        }
        dbService.writeToDB(customers);
    }
}

