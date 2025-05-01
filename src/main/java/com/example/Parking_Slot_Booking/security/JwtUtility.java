package com.example.Parking_Slot_Booking.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtility {

    private static final String SECRET_KEY_STRING = "!EY4Pi@FDTuT84T&Mt5i&9AAWbjMPADk";

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15 ))
                .signWith(SECRET_KEY, Jwts.SIG.HS256)
                .compact();
    }
    
    public boolean validateToken(String token, UserDetails userDetails){
        return extractUser(token).equals(userDetails.getUsername());
    }

    public String extractUser(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
