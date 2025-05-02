package com.example.Parking_Slot_Booking.repository;

import com.example.Parking_Slot_Booking.model.Bookings;
import com.example.Parking_Slot_Booking.model.ParkSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<ParkSlot, Long> {

//    @Query("Select p from ParkSlot p" +
//            "where p.mall.id = :mallId and isAvailable = true")
    List<ParkSlot> findByMallIdAndIsAvailableTrue(long id);

    List<ParkSlot> findByShopIdAndIsAvailableTrue(long id);

    ParkSlot findByUserId(long id);


    @Modifying
    @Transactional
    @Query("DELETE FROM ParkSlot p WHERE p.id = :slotId")
    int deleteSlotById(@Param("slotId") Long slotId);


}
