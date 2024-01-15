//package com.example.bookstore.controller;
//
//import com.example.bookstore.constant.ApiURL;
//import com.example.bookstore.payload.user.UserRequest;
//import com.example.bookstore.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping(ApiURL.USER)
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping(value = "/findAll")
//    public ResponseEntity<?> findAll() {
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<?> update (@PathVariable UUID id,
//                                     @RequestBody UserRequest userRequest) {
//        return ResponseEntity.ok(userService.update(id, userRequest));
//    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<?> findById (@PathVariable UUID id) {
//        return ResponseEntity.ok(userService.findById(id));
//    }
//}
