package com.example.bookstore.payload.Discount;

import lombok.Data;

@Data
public class DiscountOfUserRequest {

    private String userId;

    private long discountId;
}
