package com.example.bookstore.payload.book;

import com.example.bookstore.model.book.Book;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class BookNatureRequest {

    private String nature;

    private BigDecimal price;

    private int quality;

    private UUID bookId;

    private boolean isDeleted;
}
