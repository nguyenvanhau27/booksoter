package com.example.bookstore.payload.order;

import com.example.bookstore.model.Order;
import com.example.bookstore.model.book.BookNature;
import com.example.bookstore.model.book.StatusBook;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Data
public class OrderDetailRequest {

    private String bookNatureID;

    private int quality;

}

//    private int statusId;
