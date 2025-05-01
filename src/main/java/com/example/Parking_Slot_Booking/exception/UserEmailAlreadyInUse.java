package com.example.Parking_Slot_Booking.exception;

public class UserEmailAlreadyInUse extends RuntimeException {
    public UserEmailAlreadyInUse(String message) {
        super(message);
    }
}
