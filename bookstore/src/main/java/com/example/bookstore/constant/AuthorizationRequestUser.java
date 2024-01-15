package com.example.bookstore.constant;

import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.model.user.User;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorizationRequestUser {

    // lấy thông tin từ seciritycontenholder
    // param id user
    // do: check id equal

    @Autowired
    private UserRepository userRepository;

    public boolean checkUserId(UUID userId){

        String emailUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(emailUserLogin).orElseThrow(); // case not found this cannot happen

        if(user.getId().equals(userId)){
            return true;
        }
        return false;
    }

    public boolean checkRoleAdmin(UUID userId){

        String emailUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(emailUserLogin).orElseThrow(); // case not found this cannot happen

        if(user.getRole().equals("ROLE_ADMIN")){
            return true;
        }
        return false;
    }
}
