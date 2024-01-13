package com.example.bookstore.payload.auth;


import com.example.bookstore.payload.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;
    private UserResponse userResponse;
}
