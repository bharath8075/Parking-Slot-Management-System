package com.example.Parking_Slot_Booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ParkingSlotBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingSlotBookingApplication.class, args);
	}

}
