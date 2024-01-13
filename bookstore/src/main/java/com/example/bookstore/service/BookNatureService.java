package com.example.bookstore.service;

import com.example.bookstore.model.book.BookNature;
import com.example.bookstore.payload.book.BookNatureRequest;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.book.PriceBook;

import java.util.List;
import java.util.UUID;

public interface BookNatureService {

    MessageResponse create(BookNatureRequest bookNatureRequest);

    MessageResponse update(UUID id, BookNatureRequest bookNatureRequest);

    MessageResponse delete(UUID id);

    List<BookNature> findAllByBookId(UUID id);

    BookNature findById(UUID id);
;
    PriceBook findMinMaxPricesByBookId(String bookId);

}
