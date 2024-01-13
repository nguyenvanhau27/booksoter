package com.example.bookstore.payload.address;


import lombok.Data;

@Data
public class AddressRequest {

    private String location;
    private String type;
    private String userId;
}
