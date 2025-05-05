package com.example.Parking_Slot_Booking.service;

import com.example.Parking_Slot_Booking.model.Bookings;
import com.example.Parking_Slot_Booking.model.ParkSlot;
import com.example.Parking_Slot_Booking.repository.BookingsRepository;
import com.example.Parking_Slot_Booking.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SlotAvailabilityUpdater {

    @Autowired
    private ParkRepository parkRepo;

    @Autowired
    private BookingsRepository bookingsRepo;


    @Transactional
    @Scheduled(fixedRate = 60000)
    public void holdSlot(){
        LocalDateTime presentTime = LocalDateTime.now();
        List<Bookings> notExpiredBookings = bookingsRepo.findByNotExpired();

        for (Bookings books : notExpiredBookings){
            if(books.getStartTime().isAfter(presentTime.minusMinutes(1))){
                ParkSlot updateSlot = parkRepo.findById(books.getParkSlotId()).orElseThrow(
                        () -> new RuntimeException("hold Slot error")
                );
                updateSlot.setUser(books.getUser());
                updateSlot.setAvailable(false);

                parkRepo.save(updateSlot);
            }
        }
    }

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void releaseExpiredSlots(){

        LocalDateTime presentTime = LocalDateTime.now();
//        LocalDateTime bufferTime = presentTime.minusMinutes(2);
        List<Bookings> expiredBookings = bookingsRepo.findByExpiredAndEndTime(presentTime);


        for (Bookings books : expiredBookings){
            ParkSlot updateSlot = parkRepo.findById(books.getParkSlotId()).orElseThrow(
                    ()-> new RuntimeException("Conflict in Slot Availability updater!!"));

            updateSlot.setAvailable(true);
            updateSlot.setUser(null);
            parkRepo.save(updateSlot);

            books.setExpired(true);
            bookingsRepo.save(books);

        }

    }

}
