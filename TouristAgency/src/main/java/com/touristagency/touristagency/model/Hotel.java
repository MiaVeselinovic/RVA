package com.touristagency.touristagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "HOTEL_ID_GEN", sequenceName = "HOTEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOTEL_ID_GEN")

    private int id;
    private String name;
    private int numberOfStars;
    private String description;

    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private List<Arrangement> arrangements;

    @ManyToOne
    @JoinColumn(name = "destination")
    private Destination destination;

    public Hotel() {
        super();
    }

    public Hotel(int id, String name, int numberOfStars, String description, List<Arrangement> arrangements, Destination destination) {
        super();
        this.id = id;
        this.name = name;
        this.numberOfStars = numberOfStars;
        this.description = description;
        this.arrangements = arrangements;
        this.destination = destination;
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

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int number_of_stars) {
        this.numberOfStars = number_of_stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Arrangement> getArrangements() {
        return arrangements;
    }

    public void setArrangements(List<Arrangement> arrangements) {
        this.arrangements = arrangements;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
