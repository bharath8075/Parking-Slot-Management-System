package com.example.Parking_Slot_Booking.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "user-parkSlot")
    private ParkSlot parkSlot;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-bookings")
    private List<Bookings> bookingss;

    public User(long id, String userName, String email, String password, String role, ParkSlot parkSlot, List<Bookings> bookingss) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.parkSlot = parkSlot;
        this.bookingss = bookingss;
    }

    public User() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ParkSlot getParkSlot() {
        return parkSlot;
    }

    public void setParkSlot(ParkSlot parkSlot) {
        this.parkSlot = parkSlot;
    }

    public List<Bookings> getBookingss() {
        return bookingss;
    }

    public void setBookingss(List<Bookings> bookingss) {
        this.bookingss = bookingss;
    }
}
