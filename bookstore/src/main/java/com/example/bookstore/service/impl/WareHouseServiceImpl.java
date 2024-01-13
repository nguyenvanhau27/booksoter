package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.model.WareHouse;
import com.example.bookstore.model.book.BookNature;
import com.example.bookstore.model.book.StatusBook;
import com.example.bookstore.model.enumm.StatusEnum;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.WareHouseRequest;
import com.example.bookstore.repository.BookNatureRepository;
import com.example.bookstore.repository.StatusRepository;
import com.example.bookstore.repository.WareHouseRepository;
import com.example.bookstore.service.WareHouseService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WareHouseServiceImpl implements WareHouseService {

    @Autowired
    private WareHouseRepository wareHouseRepository;
    @Autowired
    private BookNatureRepository bookNatureRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public MessageResponse create(WareHouseRequest wareHouseRequest) {

        WareHouse wareHouse = new WareHouse();

        wareHouse.setAuthor(wareHouseRequest.getAuthor());
        wareHouse.setRawPrice(wareHouseRequest.getRawPrice());
        wareHouse.setSalePrice(wareHouseRequest.getSalePrice());
        wareHouse.setQuality(wareHouseRequest.getQuality());
        wareHouse.setValue(wareHouseRequest.getValue());
        wareHouse.setIo(wareHouseRequest.isIo());
        wareHouse.setBookCode(wareHouseRequest.getBookCode());
        wareHouse.setNote(wareHouseRequest.getNote());

        if(wareHouseRequest.getDate() == null){
            wareHouseRequest.setDate(new Date());
        }

        wareHouse.setDate(wareHouseRequest.getDate());

        BookNature bookNature = bookNatureRepository.findById(UUID.fromString(wareHouseRequest.getBookNatureId()))
                .orElseThrow();
        wareHouse.setBookNature(bookNature);

        StatusBook statusBook = statusRepository.findById(wareHouseRequest.getStatusBookId()).orElseThrow();
        wareHouse.setStatusBook(statusBook);

        wareHouseRepository.save(wareHouse);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.WARE_HOUSE);
    }

    @Override
    public MessageResponse update(long id, WareHouseRequest wareHouseRequest) {

        WareHouse wareHouseOld = wareHouseRepository.findById(id).orElseThrow();

        wareHouseOld.setAuthor(wareHouseRequest.getAuthor());
        wareHouseOld.setRawPrice(wareHouseRequest.getRawPrice());
        wareHouseOld.setSalePrice(wareHouseRequest.getSalePrice());
        wareHouseOld.setQuality(wareHouseRequest.getQuality());
        wareHouseOld.setValue(wareHouseRequest.getValue());
        wareHouseOld.setIo(wareHouseRequest.isIo());
        wareHouseOld.setBookCode(wareHouseRequest.getBookCode());
        wareHouseOld.setNote(wareHouseRequest.getNote());

        if(wareHouseRequest.getDate() == null){
            wareHouseRequest.setDate(new Date());
        }

        wareHouseOld.setDate(wareHouseRequest.getDate());

        BookNature bookNature = bookNatureRepository.findById(UUID.fromString(wareHouseRequest.getBookNatureId()))
                .orElseThrow();
        wareHouseOld.setBookNature(bookNature);

        StatusBook statusBook = statusRepository.findById(wareHouseRequest.getStatusBookId()).orElseThrow();
        wareHouseOld.setStatusBook(statusBook);

        wareHouseRepository.save(wareHouseOld);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.WARE_HOUSE);
    }

    @Override
    public MessageResponse delete(long id) {

        WareHouse wareHouseDeleteStatus = wareHouseRepository.findById(id).orElseThrow();

        if(!wareHouseDeleteStatus.getStatusBook().getName().equals(StatusEnum.Delete)){
            StatusBook statusDelete = statusRepository.findByName(String.valueOf(StatusEnum.Delete)).orElseThrow();
            wareHouseDeleteStatus.setStatusBook(statusDelete);
            wareHouseRepository.save(wareHouseDeleteStatus);
        }

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.WARE_HOUSE);
    }

//    @Override
//    public List<WareHouse> findAllByCodeBook(String codebook) {
//        return null;
//    }

    @Override
    public List<WareHouse> findAllByBookNatureId(UUID bookNatureId) {

        List<WareHouse> wareHouseList = wareHouseRepository.findByBookNature_Id(bookNatureId).stream().toList();

        return wareHouseList;
    }

    @Override
    public WareHouse findById(long id) {

        WareHouse wareHouseDeleteStatus = wareHouseRepository.findById(id).orElseThrow();

        return wareHouseDeleteStatus;
    }


}
