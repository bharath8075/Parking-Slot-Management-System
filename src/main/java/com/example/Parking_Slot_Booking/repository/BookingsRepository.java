package com.example.Parking_Slot_Booking.repository;

import com.example.Parking_Slot_Booking.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findByUserId(long id);
}
