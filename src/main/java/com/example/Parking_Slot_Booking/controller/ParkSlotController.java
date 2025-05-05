package com.example.Parking_Slot_Booking.controller;

import com.example.Parking_Slot_Booking.dto.BookSlotDto;
import com.example.Parking_Slot_Booking.dto.SlotInfoDto;
import com.example.Parking_Slot_Booking.exception.UserNotBookedException;
import com.example.Parking_Slot_Booking.exception.UserNotFoundException;
import com.example.Parking_Slot_Booking.service.ParkSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkSlotController {

    @Autowired
    private ParkSlotService parkService;

    @GetMapping("/shop-or-mall-name")
    public ResponseEntity<?> showSlots(@RequestParam String name){
        return ResponseEntity.ok(parkService.showSlots(name));
    }

    @PostMapping("/bookslot/{userId}")
    public ResponseEntity<String> bookSlot(@PathVariable long userId, @RequestBody BookSlotDto bookingInfo){

        try {
            String message = parkService.bookSlot(userId, bookingInfo);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/get-info/{userId}")
    public ResponseEntity<?> getInfo(@PathVariable long userId){

        try{
            return ResponseEntity.ok(parkService.getInfo(userId));
        } catch (UserNotFoundException | UserNotBookedException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/booking-records/{userId}")
    public ResponseEntity<?> getBookingRecords(@PathVariable long userId){

        return ResponseEntity.ok(parkService.getBookingRecords(userId));
    }

    @GetMapping("/available-slots")
    public ResponseEntity<?> showAvailableSlots() {
        List<SlotInfoDto> availableSlots = null;
        try {
            availableSlots = parkService.showAvailableSlots();
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(availableSlots);
    }

    @PutMapping("/cancel-booking/{slotId}")
    public ResponseEntity<?> cancelBooking(@RequestHeader("Authorization") String token, long slotId){
        try{
            parkService.cancelBooking(token, slotId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("Slot cancelled!!");
    }

    @GetMapping("/show-available-slots")
    public ResponseEntity<?> showAvailableStatus(@RequestBody BookSlotDto bookingInfo){
        String message = null;
        try {
            message = parkService.showAvailableSlot(bookingInfo);
        } catch ( Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(message);
    }
}
