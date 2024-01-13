package com.example.bookstore.payload.book;

import com.example.bookstore.constant.PageDefault;
import lombok.Data;

@Data
public class FilterSearchBookRequest {

    private String name;
    private String topicName;
    private int rating;
    private String priceRange;
    private String sortOption;
    private String author;
    private String status;

    private int no = PageDefault.NO; // Set default value for 'no'
    private int limit = PageDefault.LIMIT; // Set default value for 'limit'

}
