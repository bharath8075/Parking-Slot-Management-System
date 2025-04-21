package com.example.Parking_Slot_Booking.controller;

import com.example.Parking_Slot_Booking.service.ParkSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
public class ParkSlotController {

    @Autowired
    private ParkSlotService parkService;

    @GetMapping("/shop-or-mall-name")
    public ResponseEntity<?> showSlots(@RequestParam String name){
        return ResponseEntity.ok(parkService.showSlots(name));
    }

    @PostMapping("/bookslot")
    public ResponseEntity<String> bookSlot(@RequestParam long userId, @RequestParam long slotId){

        return ResponseEntity.ok(parkService.bookSlot(userId, slotId));
    }
}
