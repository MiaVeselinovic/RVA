package com.touristagency.touristagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Agency implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "AGENCY_ID_GEN", sequenceName = "AGENCY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGENCY_ID_GEN")

    private int id;
    private String name;
    private String address;
    private String contact;

    @OneToMany(mappedBy = "agency")
    @JsonIgnore
    private List<Arrangement> arrangements;

    public Agency() {
        super();
    }

    public Agency(int id, String name, String address, String contact, List<Arrangement> arrangements) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.arrangements = arrangements;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Arrangement> getArrangements() {
        return arrangements;
    }

    public void setArrangements(List<Arrangement> arrangements) {
        this.arrangements = arrangements;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
