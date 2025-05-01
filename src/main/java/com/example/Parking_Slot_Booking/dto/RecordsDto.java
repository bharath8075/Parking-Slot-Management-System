package com.example.Parking_Slot_Booking.dto;

public class RecordsDto {

    private Long slotId;
    private String shopName;

    public RecordsDto(){
        super();
    }
    public RecordsDto(Long slotId, String shopName) {
        this.slotId = slotId;
        this.shopName = shopName;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
