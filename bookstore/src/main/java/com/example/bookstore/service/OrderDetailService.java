package com.example.bookstore.service;

import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.order.OrderDetailRequest;

public interface OrderDetailService {

    MessageResponse create(long orderId, OrderDetailRequest orderDetailRequest);
}
