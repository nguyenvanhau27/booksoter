package com.example.bookstore.payload.book;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BookRequest {

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
    private int publishingYear;
    private String language;
    private String weight;
    private String packagingSize;
    private int numberOfPage;

}
