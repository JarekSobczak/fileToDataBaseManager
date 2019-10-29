package pl.brite.ownProject.fileToDb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import lombok.*;

import java.util.List;

@Data
public class Customer {

    private Integer id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("age")
    private Integer age;
    @JacksonXmlElementWrapper(localName = "contacts")
    private List<Contact> contacts;

    private String city;
}

