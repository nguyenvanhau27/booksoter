package com.example.bookstore.service;

import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.order.OrderRequest;

public interface OrderService {


    MessageResponse create(OrderRequest orderRequest);

    MessageResponse update(long id, OrderRequest orderRequest);

    MessageResponse delete(long id);



}
