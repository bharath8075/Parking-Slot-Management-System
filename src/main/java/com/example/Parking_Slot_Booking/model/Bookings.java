package com.example.Parking_Slot_Booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private boolean expired;

    public Bookings(long id, long parkSlotId, User user, LocalDateTime startTime, LocalDateTime endTime, boolean expired) {
        this.id = id;
        this.parkSlotId = parkSlotId;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.expired = expired;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
