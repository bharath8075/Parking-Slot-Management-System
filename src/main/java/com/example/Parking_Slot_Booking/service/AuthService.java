package com.example.Parking_Slot_Booking.service;

import com.example.Parking_Slot_Booking.dto.UserDto;
import com.example.Parking_Slot_Booking.exception.UserEmailAlreadyInUse;
import com.example.Parking_Slot_Booking.model.User;
import com.example.Parking_Slot_Booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<?> registerUser(UserDto user) {
        Optional<User> optionalUser = userRepo.findByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            throw new UserEmailAlreadyInUse("Email has already registered");
        }
        if(user.getPassword() == null || user.getPassword().trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must not be empty!!");

        }

        User newUser = new User();
        newUser.setUserName(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole("USER");
        userRepo.save(newUser);
        return ResponseEntity.ok("User registered sucessfully!!");
    }
}
