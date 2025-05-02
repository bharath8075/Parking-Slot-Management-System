package com.example.Parking_Slot_Booking.repository;

import com.example.Parking_Slot_Booking.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findByUserId(long id);

    @Modifying
    @Transactional
    @Query("Select b from bookings as b where b.park_slot_id == :slotId " +
            "and b.startTime < :newEndTime and b.endTime > :newStartTime")
    List<Bookings> findBookingConflicts(@Param("slotId") long slotId,
            @Param("newStartTime")LocalDateTime newStartTime,
            @Param("newEndTime") LocalDateTime newEndTime);
}
