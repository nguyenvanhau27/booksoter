package com.example.bookstore.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {

//    String generateToken(UserDetails userDetails);
    String generateToken(Authentication authentication, UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    public String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails);

}
