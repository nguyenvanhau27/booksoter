package com.example.bookstore.controller.old;


import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURL.PAYMENT)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "")
    private ResponseEntity<?> findById(){
        return ResponseEntity.ok(paymentService.findAll());
    }



}
