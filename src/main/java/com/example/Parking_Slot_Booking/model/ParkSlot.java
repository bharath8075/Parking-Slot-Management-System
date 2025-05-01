package com.example.Parking_Slot_Booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ParkSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean isAvailable = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-parkSlot")
    private User user;

    @OneToOne
    private Shop shop;

    @ManyToOne
    @JsonBackReference(value = "mall-slot")
    private Mall mall;



    public ParkSlot(){
        super();
    }
    public ParkSlot(long id, boolean isAvailable, User user, Shop shop, Mall mall) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.user = user;
        this.shop = shop;
        this.mall = mall;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Mall getMall() {
        return mall;
    }

    public void setMall(Mall mall) {
        this.mall = mall;
    }
}
