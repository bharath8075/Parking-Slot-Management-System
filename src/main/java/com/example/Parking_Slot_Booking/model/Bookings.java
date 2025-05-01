package com.example.Parking_Slot_Booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;


    private long parkSlotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-bookings")
    private User user;

    public Bookings(long id, long parkSlotId, User user){
        this.id = id;
        this.parkSlotId = parkSlotId;
        this.user = user;
    }

    public Bookings(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParkSlotId() {
        return parkSlotId;
    }

    public void setParkSlotId(long parkSlotId) {
        this.parkSlotId = parkSlotId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
