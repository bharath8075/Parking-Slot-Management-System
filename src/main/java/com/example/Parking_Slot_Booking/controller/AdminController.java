package com.example.Parking_Slot_Booking.controller;

import com.example.Parking_Slot_Booking.dto.AddSlotDto;
import com.example.Parking_Slot_Booking.dto.BookingsInfoDto;
import com.example.Parking_Slot_Booking.dto.SlotInfoDto;
import com.example.Parking_Slot_Booking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-slot")
    public ResponseEntity<?> addSlot(@RequestBody AddSlotDto slotInfo){
        if(slotInfo.getMallName().isEmpty() || slotInfo.getShopName().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Both Shop name and mall name are Required");
        }
        try {
            adminService.addSlot(slotInfo);
        } catch (Exception e){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return  ResponseEntity.ok("Slot added successful!!");
    }

    @DeleteMapping("delete-slot/{slotId}")
    public ResponseEntity<?> deleteSlot(@PathVariable Long slotId){

        if(slotId == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Slot ID can not be null");
        }

        try {
            adminService.deleteSlot(slotId);
            return ResponseEntity.ok("Slot deleted!!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    //view slot was unnecessary => created by mistake or carelessness
    @GetMapping("view-all-slot")
    public ResponseEntity<List<SlotInfoDto>> viewAllSlot(){
        return ResponseEntity.ok(adminService.viewAllSlot());
    }

    @GetMapping("view-all-bookings")
    public ResponseEntity<List<BookingsInfoDto>> viewAllBookings(){
        return ResponseEntity.ok(adminService.viewAllBookings());
    }

}
