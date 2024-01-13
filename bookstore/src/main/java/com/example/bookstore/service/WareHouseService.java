package com.example.bookstore.service;

import com.example.bookstore.model.WareHouse;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.WareHouseRequest;

import java.util.List;
import java.util.UUID;

public interface WareHouseService {

    MessageResponse create(WareHouseRequest wareHouseRequest);

    MessageResponse update(long id, WareHouseRequest wareHouseRequest);

    MessageResponse delete(long id);

//    List<WareHouse> findAllByCodeBook(String codebook);

    List<WareHouse> findAllByBookNatureId(UUID bookNatureId);

    WareHouse findById(long id);
}
