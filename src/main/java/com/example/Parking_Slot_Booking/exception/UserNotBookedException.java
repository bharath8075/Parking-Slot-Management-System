package com.example.Parking_Slot_Booking.exception;

public class UserNotBookedException extends RuntimeException{
    public UserNotBookedException(String message){
        super(message);
    }
}
