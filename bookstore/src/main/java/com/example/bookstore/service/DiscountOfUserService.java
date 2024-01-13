package com.example.bookstore.service;

import com.example.bookstore.model.discount.DiscountOfUser;
import com.example.bookstore.payload.Discount.DiscountOfUserRequest;
import com.example.bookstore.payload.MessageResponse;

public interface DiscountOfUserService {

    MessageResponse create(DiscountOfUserRequest discountOfUserRequest);

    MessageResponse update(long id, DiscountOfUserRequest discountOfUserRequest);

    MessageResponse delete(long id);

    DiscountOfUser findById(long id);


}