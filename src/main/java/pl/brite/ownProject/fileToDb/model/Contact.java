package pl.brite.ownProject.fileToDb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {


    private Integer id;

    private Integer idCustomer;

    private int type;

    private String contact_data;


    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContact_data() {
        return contact_data;
    }

    public void setContact_data(String contact_data) {
        this.contact_data = contact_data;
    }

    public void update(String key, String value) {
        if ("type".equalsIgnoreCase(key)) {
            type = Integer.valueOf(value);
        } else {
            this.contact_data = value;
        }
    }
}

