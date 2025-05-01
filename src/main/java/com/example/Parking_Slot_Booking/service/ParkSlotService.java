package com.example.Parking_Slot_Booking.service;

import com.example.Parking_Slot_Booking.dto.InfoDto;
import com.example.Parking_Slot_Booking.dto.RecordsDto;
import com.example.Parking_Slot_Booking.dto.SlotInfoDto;
import com.example.Parking_Slot_Booking.exception.UserNotBookedException;
import com.example.Parking_Slot_Booking.exception.UserNotFoundException;
import com.example.Parking_Slot_Booking.model.*;
import com.example.Parking_Slot_Booking.repository.*;
import com.example.Parking_Slot_Booking.security.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    private BookingsRepository bookingsRepo;

    @Autowired
    private JwtUtility jwtUtil;

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

        if(shop == null && mall == null){
            return Collections.emptyList();
        }

        return availableSlots;
    }


    public String bookSlot(long userId, long slotId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isEmpty()){
            return "User not found";
        }

        Optional<ParkSlot> optionalSlot = parkRepo.findById(slotId);
        if(optionalSlot.isPresent()){
            ParkSlot slot = optionalSlot.get();
            Bookings book = new Bookings();
            User user = optionalUser.get();
            if(slot.isAvailable()){
                slot.setAvailable(false);
                slot.setUser(user);
                parkRepo.save(slot);
                //setting book record while booking a slot
                book.setParkSlotId(slot.getId());
                book.setUser(slot.getUser());
                bookingsRepo.save(book);
                return "slot Booked";
            } else {
                return "slot already booked";
            }
        }
        return "slot not found";
    }

    public List<InfoDto> getInfo(long userId) {

        Optional<User> optionalUser = userRepo.findById(userId);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found!!");
        }

        User user = optionalUser.get();
        List<ParkSlot> parkSlots = new ArrayList<>();
            if(user.getId() == userId){
                ParkSlot parkSlot = parkRepo.findByUserId(user.getId());
              parkSlots.add(parkSlot);
            }

        List<InfoDto> responseDto = new ArrayList<>();
        for(ParkSlot ps : parkSlots){
            if(!ps.isAvailable()) {
               String shopName = ps.getShop() != null ? ps.getShop().getName() : null;
               String mallName = ps.getMall() != null ? ps.getMall().getName() : null;
                InfoDto infoDto = new InfoDto(shopName, mallName, true);
               responseDto.add(infoDto);
            }
        }

        if(responseDto.isEmpty()){
            throw new UserNotBookedException("User not booked any slot!!");
        }

        return responseDto;
    }

    public List<RecordsDto> getBookingRecords(long userId) {

        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not Found!!");
        }
        User user = optionalUser.get();
        List<Bookings> allBookings = bookingsRepo.findAll();

        List<RecordsDto> userBookings = new ArrayList<>();

        for(Bookings bookings : allBookings){
            if (bookings.getUser().getId() == userId){

                Long slotId = bookings.getParkSlotId();
                Optional<ParkSlot> optionalParkSlotarkSlot = parkRepo.findById(slotId);

                String shop = optionalParkSlotarkSlot.get().getShop().getName();

                RecordsDto recordsDto = new RecordsDto(slotId, shop);
                userBookings.add(recordsDto);
            }
        }
        return userBookings;
    }

    public ResponseEntity<?> showAvailableSlots() {
        List<SlotInfoDto> availableSlots = new ArrayList<>();
        List<ParkSlot> allslots = parkRepo.findAll();

        for(ParkSlot slot : allslots){
            if(slot.isAvailable()){
                SlotInfoDto slotInfo = new SlotInfoDto();
                slotInfo.setShopName(slot.getShop().getName());
                slotInfo.setMallName(slot.getMall().getName());
                slotInfo.setSlotStatus("Available");

                availableSlots.add(slotInfo);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currently slots are not available");
        }

        return ResponseEntity.ok(availableSlots);
    }

    public ResponseEntity<?> cancelBooking(String token, long slotId) {
        String email = jwtUtil.extractUser(token);

        Optional<ParkSlot> optCurrentSlot = parkRepo.findById(slotId);

        Optional<User> optUser = userRepo.findById(optCurrentSlot.get().getUser().getId());

        if(!optUser.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter a valid slot ID");
        }
        User user = optUser.get();

        if(!user.getEmail().equals(email)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Slot ID - "+ slotId + " is not booked by "+ user.getUserName());
        }

        ParkSlot currentSlot = optCurrentSlot.get();
        currentSlot.setUser(null);
        currentSlot.setAvailable(true);

        parkRepo.save(currentSlot);

        return ResponseEntity.ok("Slot cancelled succesfully");
    }
}
