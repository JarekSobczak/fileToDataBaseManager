package pl.brite.ownProject.fileToDb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {


    private Integer id;

    private String name;

    private String surname;

    private String age;

    private String city;

    public Customer(String name, String surname, String age, String city, List<Contact> contacts) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.contacts = contacts;
    }

    public Customer() {
    }

    @JacksonXmlElementWrapper(localName = "contacts")
    private List<Contact> contacts;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

