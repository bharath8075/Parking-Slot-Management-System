package com.example.Parking_Slot_Booking.exception;

public class UserNotFoundException extends RuntimeException{
    public  UserNotFoundException(String message){
        super(message);
    }
}
