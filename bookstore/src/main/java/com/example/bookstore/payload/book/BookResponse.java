package com.example.bookstore.payload.book;

import com.example.bookstore.model.book.StatusBook;
import com.example.bookstore.model.book.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private UUID id;
    private String name;
    private String author;
    private String information;
    private String descriptions;
    private String topicName;

    //......

    private Integer countPayed;
    private Integer rating;
    private String statusName ;
    private String supplier;
    private String publishingCompany;
    private String form;
    @Temporal(TemporalType.DATE)
    private Date publishingYear;
    private String language;
    private String weight;
    private String packagingSize;
    private int numberOfPage;
}
