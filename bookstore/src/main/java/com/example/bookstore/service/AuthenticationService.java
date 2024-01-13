package com.example.bookstore.service;

import com.example.bookstore.payload.auth.JwtAuthenticationResponse;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.auth.SigninRequest;
import com.example.bookstore.payload.auth.SignupRequest;
import com.example.bookstore.payload.user.UserResponse;

public interface AuthenticationService {

    public UserResponse signup(SignupRequest signupRequest) throws Exception;

    public JwtAuthenticationResponse signin(SigninRequest signinRequest);

    MessageResponse changePassword(String newPassword);
}
