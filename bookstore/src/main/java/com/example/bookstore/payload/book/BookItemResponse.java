package com.example.bookstore.payload.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookItemResponse {

    //name
    private String name; //name of book
    //image
    private String imageName; //image name is name of album
    //price
    private String price; //price from priceMax link priceMin ex: priceMin = 100000.00, priceMax = 150000.00 then price = '100000 - 150000'
    // discount
    private float discount; //join percent of discountBook
    //rating
    private int rating; //rating of book

    private String status;

}
