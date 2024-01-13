package com.example.bookstore.service;

import com.example.bookstore.model.discount.Discount;
import com.example.bookstore.payload.Discount.DiscountRequest;
import com.example.bookstore.payload.MessageResponse;

import java.util.List;

public interface DiscountService {

    MessageResponse create(DiscountRequest discountRequest);

    MessageResponse update(long id, DiscountRequest discountRequest);

    MessageResponse delete(long id);

    List<Discount> findByType(String type);

    List<Discount> findByIsDeleteNot();

    List<Discount> findAll();

    Discount findById(long id);
}
