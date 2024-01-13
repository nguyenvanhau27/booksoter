package com.example.bookstore.payload;

import lombok.Data;

@Data
public class CommentRequest {

    private Integer likes;
    private Integer rating;
    private String image;
    private String commentContent;
    private String bookId;
    private String userId;

}
