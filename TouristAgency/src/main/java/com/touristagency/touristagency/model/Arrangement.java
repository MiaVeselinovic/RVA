package com.touristagency.touristagency.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Arrangement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ARRANGEMENT_ID_GEN", sequenceName = "ARRANGEMENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARRANGEMENT_ID_GEN")

    private int id;
    private double total_cost;
    private boolean payed;
    private Date date_realization;

    @ManyToOne
    @JoinColumn(name = "hotel")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "agency")
    private Agency agency;

    public Arrangement() {
        super();
    }

    public Arrangement(int id, double total_cost, boolean payed, Date date_realization, Hotel hotel,
                    Agency agency) {
        super();
        this.id = id;
        this.total_cost = total_cost;
        this.payed = payed;
        this.date_realization = date_realization;
        this.hotel = hotel;
        this.agency = agency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Date getDate_realization() {
        return date_realization;
    }

    public void setDate_realization(Date date_realization) {
        this.date_realization = date_realization;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
