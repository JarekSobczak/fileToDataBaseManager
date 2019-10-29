package pl.brite.ownProject.fileToDb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Contact {

    private Integer id;

    private Integer idCustomer;
    @JsonProperty("type")
    private int type;
    @JsonProperty("contact")
    private String contact;
}
