package com.touristagency.touristagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Destination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "DESTINATION_ID_GEN", sequenceName = "DESTINATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DESTINATION_ID_GEN")

    private int id;
    private String place;
    private String country;
    private String description;

    @OneToMany(mappedBy = "destination")
    @JsonIgnore
    private List<Hotel> hotels;

    public Destination() {
        super();
    }

    public Destination(int id, String place, String country, String description, List<Hotel> hotels) {
        super();
        this.id = id;
        this.place = place;
        this.country = country;
        this.description = description;
        this.hotels = hotels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
