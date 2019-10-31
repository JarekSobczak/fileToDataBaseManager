package pl.brite.ownProject.fileToDb;

public class Constants {

    public static final String URL = "jdbc:mysql://localhost:3306/file_to_db?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8&allowPublicKeyRetrieval=true";

    public static final String USERNAME = "root";
    public static final String PASSWORD = "Iceicle44";

    public static final String CREATE_CONTACT_TABLE = "CREATE TABLE IF NOT EXISTS contact(" +
            "id INT AUTO_INCREMENT," +
            "id_customer INT NOT NULL," +
            "type INT NOT NULL CHECK(type BETWEEN 0 AND 3)," +
            "contact_data VARCHAR(40)," +
            "CONSTRAINT contact_pk PRIMARY KEY (id)," +
            "CONSTRAINT contact_fk FOREIGN KEY(id_customer) REFERENCES customer (id));";

    public static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE IF NOT EXISTS customer(" +
            "id INT AUTO_INCREMENT," +
            "name VARCHAR(55)NOT NULL," +
            "surname VARCHAR(55) NOT NULL," +
            "age VARCHAR(3)," +
            "city VARCHAR(45)," +
            "CONSTRAINT customer_pk PRIMARY KEY (id));";

    public static final String INSERT_INTO_CUSTOMER_SQL = "INSERT INTO customer(name,surname,age,city)VALUES(?,?,?,?)";
    public static final String INSERT_INTO_CONTACT_SQL = "INSERT INTO contact(id_customer,type,contact_data)VALUES(?,?,?)";

    public static final String SELECT_FROM_CUSTOMER = "Select*from customer";
    public static final String SELECT_FROM_CONTACT = "Select*from contact";
}
