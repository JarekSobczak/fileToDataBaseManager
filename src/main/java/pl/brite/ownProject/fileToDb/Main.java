package pl.brite.ownProject.fileToDb;

import pl.brite.ownProject.fileToDb.service.Manager;

public class Main {

    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.writeFromFileToDb();

    }
}
