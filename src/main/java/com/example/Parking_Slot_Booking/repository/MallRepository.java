package com.example.Parking_Slot_Booking.repository;

import com.example.Parking_Slot_Booking.model.Mall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MallRepository extends JpaRepository<Mall, Long> {
    Mall findByName(String name);
}
