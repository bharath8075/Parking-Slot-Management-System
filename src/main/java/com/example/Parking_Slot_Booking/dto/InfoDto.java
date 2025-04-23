package com.example.Parking_Slot_Booking.dto;

public class InfoDto {

    private String shop;

    private String mall;

    private boolean isBooked;

    public InfoDto(String shop, String mall, boolean isBooked) {
        this.shop = shop;
        this.mall = mall;
        this.isBooked = isBooked;
    }

    public InfoDto(){
        super();
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
