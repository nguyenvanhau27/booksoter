package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookTag;
import com.example.bookstore.model.book.Tag;
import com.example.bookstore.payload.book.BookTagRequest;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.BookTagRepository;
import com.example.bookstore.repository.TagRepository;
import com.example.bookstore.service.BookTagService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookTagServiceImpl implements BookTagService {

    @Autowired
    private BookTagRepository bookTagRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TagRepository tagRepository;


    @Override
    public MessageResponse create(BookTagRequest bookTagRequest) {

        BookTag bookTag = new BookTag();

        Book book = bookRepository.findById(UUID.fromString(bookTagRequest.getBookId())).orElseThrow();

        Tag tag = tagRepository.findById(bookTagRequest.getTagId()).orElseThrow();

        if(bookTagRepository.existsByTagAndBook(bookTagRequest.getTagId(),
                UUID.fromString(bookTagRequest.getBookId()))){
            return new MessageResponse(HttpServletResponse.SC_CONFLICT,"Exists", ApiURL.BOOK_TAG);
        }

        bookTag.setBook(book);
        bookTag.setTag(tag);

        bookTagRepository.save(bookTag);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.BOOK_TAG);
    }

    @Override
    public MessageResponse update(int id, BookTagRequest bookTagRequest) {

        BookTag bookTagOld = bookTagRepository.findById(id).orElseThrow();

        Book book = bookRepository.findById(UUID.fromString(bookTagRequest.getBookId())).orElseThrow();

        Tag tag = tagRepository.findById(bookTagRequest.getTagId()).orElseThrow();

        if(bookTagRepository.existsByTagAndBook(bookTagRequest.getTagId(),
                UUID.fromString(bookTagRequest.getBookId()))){
            return new MessageResponse(HttpServletResponse.SC_CONFLICT,"Exists", ApiURL.BOOK_TAG);
        }

        bookTagOld.setBook(book);
        bookTagOld.setTag(tag);

        bookTagRepository.save(bookTagOld);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.BOOK_TAG);
    }

    @Override
    public MessageResponse delete(int id) {

        bookTagRepository.deleteById(id);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.BOOK_TAG);
    }

    @Override
    public List<BookTag> findByTagId(int tagId) {

        List<BookTag> bookTagList = bookTagRepository.findByTag_Id(tagId).stream().toList();

        return bookTagList;
    }

    @Override
    public List<BookTag> findByBookId(String bookId) {

        List<BookTag> bookTagList = bookTagRepository.findByBook_Id(UUID.fromString(bookId)).stream().toList();

        return bookTagList;
    }

    @Override
    public BookTag findById(int id) {

        BookTag bookTag = bookTagRepository.findById(id).orElseThrow();

        return bookTag;
    }

    @Override
    public BookTag findByBookIdAndTagId(BookTagRequest bookTagRequest) {

        BookTag bookTag = bookTagRepository.findByBook_IdAndTag_Id(UUID.fromString(bookTagRequest.getBookId()),
                bookTagRequest.getTagId()).orElseThrow();

        return bookTag;
    }
}
