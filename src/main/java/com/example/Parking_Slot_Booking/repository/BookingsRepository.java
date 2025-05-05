package com.example.Parking_Slot_Booking.repository;

import com.example.Parking_Slot_Booking.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findByUserId(long id);


    @Query(value = "Select * FROM bookings WHERE park_slot_id = :slotId " +
            "and start_time < :newEndTime AND end_time > :newStartTime", nativeQuery = true)
    List<Bookings> findBookingConflicts(@Param("slotId") long slotId,
            @Param("newStartTime")LocalDateTime newStartTime,
            @Param("newEndTime") LocalDateTime newEndTime);

    @Query(value = "SELECT * FROM bookings WHERE expired = false AND end_time < :newTime", nativeQuery = true)
    List<Bookings> findByExpiredAndEndTime(@Param("newTime") LocalDateTime newTime);


    List<Bookings> findByExpiredFalseAndEndTimeBefore(LocalDateTime time);
    @Query(value = "SELECT * FROM bookings WHERE expired = false", nativeQuery = true)
    List<Bookings> findByNotExpired();

//    List<Bookings> findByExpiredFalse();
}
