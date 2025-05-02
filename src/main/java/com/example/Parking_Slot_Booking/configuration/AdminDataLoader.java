package com.example.Parking_Slot_Booking.configuration;

import com.example.Parking_Slot_Booking.model.User;
import com.example.Parking_Slot_Booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(!userRepo.existsByEmail("admin@gmail.com")){
            User admin = new User();

            admin.setUserName("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            userRepo.save(admin);
        }

        if(!userRepo.existsByEmail("superAdmin@gmail.com")){
            User superAdmin = new User();

            superAdmin.setUserName("SuperAdmin");
            superAdmin.setEmail("superAdmin@gmail.com");
            superAdmin.setPassword(passwordEncoder.encode("superAdmin123"));
            superAdmin.setRole("SUPER_ADMIN");

            userRepo.save(superAdmin);
        }
    }
}
