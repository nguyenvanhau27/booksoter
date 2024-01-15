package com.example.bookstore.service;

import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.user.UserRequest;
import com.example.bookstore.payload.user.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;


public interface UserService {

    UserDetailsService userDetailsService();

    List<UserResponse> findAll();

    UserResponse findById(UUID id);

    MessageResponse update(UUID id, UserRequest userRequest);

    Boolean existsEmail(String email);



//    MessageResponse delete(UUID id);

//    MessageResponse block(UUID id);



}
