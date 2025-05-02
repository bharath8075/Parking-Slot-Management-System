package com.example.Parking_Slot_Booking.dto;

import java.time.LocalDateTime;

public class BookSlotDto {

    private Long slotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookSlotDto(Long slotId, LocalDateTime startTime, LocalDateTime endTime) {
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public BookSlotDto() {
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
