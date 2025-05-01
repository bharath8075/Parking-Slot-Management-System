package com.example.Parking_Slot_Booking.dto;

public class AddSlotDto {

    private String shopName;
    private String mallName;

    public AddSlotDto(String shopName, String mallName) {
        this.shopName = shopName;
        this.mallName = mallName;
    }

    public AddSlotDto() {
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }
}
