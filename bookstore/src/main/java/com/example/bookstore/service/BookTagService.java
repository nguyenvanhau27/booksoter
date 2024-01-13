package com.example.bookstore.service;

import com.example.bookstore.model.book.BookTag;
import com.example.bookstore.payload.book.BookTagRequest;
import com.example.bookstore.payload.MessageResponse;

import java.util.List;

public interface BookTagService {


    MessageResponse create(BookTagRequest bookTagRequest);

    MessageResponse update(int id, BookTagRequest bookTagRequest);

    MessageResponse delete(int id);

    List<BookTag> findByTagId(int tagId);

    List<BookTag> findByBookId(String bookId);

    BookTag findById(int id);

    BookTag findByBookIdAndTagId(BookTagRequest bookTagRequest);
}
