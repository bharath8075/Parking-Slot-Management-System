package com.example.Parking_Slot_Booking.service;

import com.example.Parking_Slot_Booking.model.Mall;
import com.example.Parking_Slot_Booking.model.ParkSlot;
import com.example.Parking_Slot_Booking.model.Shop;
import com.example.Parking_Slot_Booking.model.User;
import com.example.Parking_Slot_Booking.repository.MallRepository;
import com.example.Parking_Slot_Booking.repository.ParkRepository;
import com.example.Parking_Slot_Booking.repository.ShopRepository;
import com.example.Parking_Slot_Booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkSlotService {

    @Autowired
    private MallRepository mallRepo;

    @Autowired
    private ParkRepository parkRepo;

    @Autowired
    private ShopRepository shopRepo;

    @Autowired
    private UserRepository userRepo;

    public List<ParkSlot> showSlots(String name) {
        List<ParkSlot> availableSlots = new ArrayList<>();
        Mall mall = mallRepo.findByName(name);
        if(mall!=null){
            availableSlots.addAll(parkRepo.findByMallIdAndIsAvailableTrue(mall.getId()));
        }

        Shop shop = shopRepo.findByName(name);
        if(shop!=null){
            availableSlots.addAll(parkRepo.findByShopIdAndIsAvailableTrue(shop.getId()));
        }
        return availableSlots;
    }


    public String bookSlot(long userId, long slotId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        Optional<ParkSlot> optionalSlot = parkRepo.findById(slotId);
        if(optionalSlot.isPresent()){
            ParkSlot slot = optionalSlot.get();
            User user = optionalUser.get();
            if(slot.isAvailable()){
                slot.setAvailable(false);
                slot.setUser(user);
                parkRepo.save(slot);
                return "slot Booked";
            } else {
                return "slot already booked";
            }
        }
        return "slot not found";
    }
}
