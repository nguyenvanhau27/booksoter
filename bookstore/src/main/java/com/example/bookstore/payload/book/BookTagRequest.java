package com.example.bookstore.payload.book;

import lombok.Data;

@Data
public class BookTagRequest {

    private String bookId;
    private int tagId;

}
