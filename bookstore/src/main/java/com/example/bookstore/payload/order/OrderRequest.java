package com.example.bookstore.payload.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private int addressId;

    private int paymentId;

    List<OrderDetailRequest> orderDetailRequestList;
}
