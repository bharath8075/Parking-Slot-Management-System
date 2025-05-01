package com.example.Parking_Slot_Booking.service;

import com.example.Parking_Slot_Booking.dto.AddSlotDto;
import com.example.Parking_Slot_Booking.dto.BookingsInfoDto;
import com.example.Parking_Slot_Booking.dto.SlotInfoDto;
import com.example.Parking_Slot_Booking.model.Bookings;
import com.example.Parking_Slot_Booking.model.Mall;
import com.example.Parking_Slot_Booking.model.ParkSlot;
import com.example.Parking_Slot_Booking.model.Shop;
import com.example.Parking_Slot_Booking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MallRepository mallRepo;

    @Autowired
    private ShopRepository shopRepo;

    @Autowired
    private ParkRepository parkRepo;

    @Autowired
    private BookingsRepository bookRepo;

    public ResponseEntity<?> addSlot(AddSlotDto slotInfo) {
        Mall mall = mallRepo.findByName(slotInfo.getMallName());
        Shop shop = shopRepo.findByName(slotInfo.getShopName());

        if (mall == null || shop == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop or Mall doesn't exist");
        }

        ParkSlot newSlot = new ParkSlot();
        newSlot.isAvailable();
        newSlot.setMall(mall);
        newSlot.setShop(shop);
        newSlot.setUser(null);

        parkRepo.save(newSlot);
        return ResponseEntity.ok("Slot added Successfully!!");
    }

    public ResponseEntity<?> deleteSlot(Long slotId) {
        Optional<ParkSlot> optSlot = parkRepo.findById(slotId);

        if(optSlot.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Slot does not exist!!");
        }
        parkRepo.deleteById(slotId);

        return ResponseEntity.ok("Slot successfully deleted!");
    }

    public ResponseEntity<List<SlotInfoDto>> viewAllSlot() {
        List<SlotInfoDto> slotInfos = new ArrayList<>();

        List<ParkSlot> slots = parkRepo.findAll();

        for(ParkSlot ps : slots){
            SlotInfoDto slot = new SlotInfoDto();
            slot.setMallName(ps.getMall().getName());
            slot.setShopName(ps.getShop().getName());

            String status = ps.isAvailable() ? "un-occupied" : "occupied";
            slot.setSlotStatus(status);

            String username = ps.getUser() == null ? "Not Booked!" : ps.getUser().getUserName();
            slot.setUsername(username);

            slotInfos.add(slot);
        }

        return ResponseEntity.ok(slotInfos);
    }

    public ResponseEntity<List<BookingsInfoDto>> viewAllBookings() {
        List<Bookings> allBookings = bookRepo.findAll();
        List<BookingsInfoDto> allBookingsResponse = new ArrayList<>();

        for(Bookings bookings : allBookings){
            BookingsInfoDto bookingsInfoDto = new BookingsInfoDto();

            bookingsInfoDto.setSlotId(bookings.getParkSlotId());
            Optional<ParkSlot> forShop = parkRepo.findById(bookings.getParkSlotId());
            bookingsInfoDto.setShopname(forShop.get().getShop().getName());
            bookingsInfoDto.setUsername(bookings.getUser().getUserName());

            allBookingsResponse.add(bookingsInfoDto);
        }

        return ResponseEntity.ok(allBookingsResponse);
    }
}
