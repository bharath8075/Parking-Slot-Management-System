package com.example.Parking_Slot_Booking.repository;

import com.example.Parking_Slot_Booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
