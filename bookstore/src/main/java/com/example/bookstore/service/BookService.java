package com.example.bookstore.service;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.payload.*;
import com.example.bookstore.payload.book.BookRequest;
import com.example.bookstore.payload.book.FilterSearchBookRequest;

import java.util.List;
import java.util.UUID;

public interface BookService {

    MessageResponse create(BookRequest bookRequest);

    Book findByBook(UUID id);

    List<Book> findAll();

    MessageResponse update(UUID id, BookRequest bookRequest);

    MessageResponse delete(UUID id);

    PaginationResponse filter(FilterSearchBookRequest filterSearchBookRequest);

    List<String> findAuthor();
}
