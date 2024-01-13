package com.example.bookstore.service;

import com.example.bookstore.model.user.Address;
import com.example.bookstore.payload.address.AddressRequest;
import com.example.bookstore.payload.MessageResponse;

import java.util.List;

public interface AddressService {

    MessageResponse create(AddressRequest addressRequest);

    MessageResponse update(int id, AddressRequest addressRequest);

    MessageResponse delete(int id);

    List<Address> findByUserId(String userId);

    Address findById(int id);
}
