package com.example.Parking_Slot_Booking.repository;

import com.example.Parking_Slot_Booking.model.ParkSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<ParkSlot, Long> {

//    @Query("Select p from ParkSlot p" +
//            "where p.mall.id = :mallId and isAvailable = true")
    List<ParkSlot> findByMallIdAndIsAvailableTrue(long id);

    List<ParkSlot> findByShopIdAndIsAvailableTrue(long id);

    ParkSlot findByUserId(long id);

}
