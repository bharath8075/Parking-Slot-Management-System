package com.example.Parking_Slot_Booking.repository;

import com.example.Parking_Slot_Booking.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByName(String name);
}
