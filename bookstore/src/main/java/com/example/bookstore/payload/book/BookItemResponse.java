package com.example.bookstore.payload.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookItemResponse {

    private BookResponse bookResponse;
    //image
    private List imageName; //image name is name of album


}
