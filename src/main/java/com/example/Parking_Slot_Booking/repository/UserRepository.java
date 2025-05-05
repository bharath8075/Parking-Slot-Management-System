package com.example.Parking_Slot_Booking.repository;

import com.example.Parking_Slot_Booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
}
