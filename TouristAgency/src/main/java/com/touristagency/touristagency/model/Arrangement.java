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
    private Date realization_date;

    @ManyToOne
    @JoinColumn(name = "hotel")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "agency")
    private Agency agency;

    public Arrangement() {
        super();
    }

    public Arrangement(int id, double total_cost, boolean payed, Date realization_date, Hotel hotel,
                    Agency agency) {
        super();
        this.id = id;
        this.total_cost = total_cost;
        this.payed = payed;
        this.realization_date= realization_date;
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

    public Date getRealization_date() {
        return realization_date;
    }

    public void setRealization_date(Date realization_date) {
        this.realization_date = realization_date;
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
