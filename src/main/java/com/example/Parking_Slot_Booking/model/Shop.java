package com.example.Parking_Slot_Booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JsonBackReference(value = "mall-shop")
    private Mall mall;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL)
    private ParkSlot parkSlot;

    Shop(){
        super();
    }
    public Shop(long id, String name, Mall mall) {
        this.id = id;
        this.name = name;
        this.mall = mall;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mall getMall() {
        return mall;
    }

    public void setMall(Mall mall) {
        this.mall = mall;
    }
}
