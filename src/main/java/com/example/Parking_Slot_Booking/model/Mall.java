package com.example.Parking_Slot_Booking.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String location;

    @OneToMany(mappedBy = "mall", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "mall-shop")
    private List<Shop> shop;

    @OneToMany(mappedBy = "mall", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "mall-slot")
    private List<ParkSlot> parkSlots;

    public long getId() {
        return id;
    }

    Mall(){
        super();
    }
    public Mall(long id, String name, String location, List<Shop> shop, List<ParkSlot> parkSlots) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.shop = shop;
        this.parkSlots = parkSlots;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Shop> getShop() {
        return shop;
    }

    public void setShop(List<Shop> shop) {
        this.shop = shop;
    }

    public List<ParkSlot> getParkSlots() {
        return parkSlots;
    }

    public void setParkSlots(List<ParkSlot> parkSlots) {
        this.parkSlots = parkSlots;
    }

}
