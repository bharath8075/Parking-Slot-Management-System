package com.example.Parking_Slot_Booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalTime;

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

    private LocalTime startTime;
    private LocalTime endTime;

    public Bookings(long id, long parkSlotId, User user, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.parkSlotId = parkSlotId;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
