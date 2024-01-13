package com.example.bookstore.service;

import com.example.bookstore.model.discount.DiscountApply;
import com.example.bookstore.payload.Discount.DiscountApplyRequest;
import com.example.bookstore.payload.MessageResponse;

public interface DiscountApplyService {

    MessageResponse create(DiscountApplyRequest discountApplyRequest);

    MessageResponse update(long id, DiscountApplyRequest discountApplyRequest);

    MessageResponse delete(long id);

    DiscountApply findById(long id);

}
