package com.example.bookstore.controller.old;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.order.OrderRequest;
import com.example.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURL.ORDER)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping(value = "")
    private ResponseEntity<?> create(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.create(orderRequest));
    }
}
