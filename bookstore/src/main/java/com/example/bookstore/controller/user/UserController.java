package com.example.bookstore.controller.user;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.user.UserRequest;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.USER)
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update (@PathVariable UUID id,
                                     @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.update(id, userRequest));
    }


}
