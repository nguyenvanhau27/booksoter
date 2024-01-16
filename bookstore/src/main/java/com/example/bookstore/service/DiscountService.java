package com.example.bookstore.service;

import com.example.bookstore.model.discount.Discount;
import com.example.bookstore.payload.Discount.DiscountRequest;
import com.example.bookstore.payload.Discount.DiscountResponse;
import com.example.bookstore.payload.MessageResponse;

import java.util.List;

public interface DiscountService {

    MessageResponse create(DiscountRequest discountRequest);

    MessageResponse update(long id, DiscountRequest discountRequest);

    MessageResponse delete(long id);

    List<DiscountResponse> findByType(String type);
    List<DiscountResponse> findByName(String name);

    List<DiscountResponse> findByIsDeleteNot();

    List<DiscountResponse> findAll();

    DiscountResponse findById(long id);
}
