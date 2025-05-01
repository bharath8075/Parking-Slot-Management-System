package com.example.Parking_Slot_Booking.dto;

public class SlotInfoDto {

    private String mallName;
    private String shopName;
    private String slotStatus;
    private String username;

    public SlotInfoDto(String mallName, String shopName, String slotStatus, String username) {
        this.mallName = mallName;
        this.shopName = shopName;
        this.slotStatus = slotStatus;
        this.username = username;
    }

    public SlotInfoDto() {
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(String slotStatus) {
        this.slotStatus = slotStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
