package com.example.bookstore.payload.Discount;

import lombok.Data;

@Data
public class DiscountApplyRequest {


    private String orderDetailId;

    private long discountId;

}
