package com.example.Parking_Slot_Booking.dto;

public class BookingsInfoDto {

    private Long slotId;
    private String username;
    private String shopname;

    public BookingsInfoDto(Long slotId, String username, String shopname) {
        this.slotId = slotId;
        this.username = username;
        this.shopname = shopname;
    }

    public BookingsInfoDto() {
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
}
