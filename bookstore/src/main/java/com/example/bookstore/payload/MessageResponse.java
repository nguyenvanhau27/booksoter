package com.example.bookstore.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageResponse {
    private int httpCode;
    private String message;
    private String path;
}
