package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookNature;
import com.example.bookstore.payload.book.BookNatureRequest;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.book.PriceBook;
import com.example.bookstore.repository.BookNatureRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookNatureService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookNatureServiceImpl implements BookNatureService {

    @Autowired
    private BookNatureRepository bookNatureRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public MessageResponse create(BookNatureRequest bookNatureRequest) {

        Book book = bookRepository.findById(bookNatureRequest.getBookId()).orElseThrow();

        BookNature bookNature = new BookNature();
        bookNature.setNature(bookNatureRequest.getNature());
        bookNature.setPrice(bookNatureRequest.getPrice());
        bookNature.setQuality(bookNatureRequest.getQuality());

        bookNature.setBook(book);

        bookNatureRepository.save(bookNature);

        updatePriceBook(String.valueOf(book.getId()));

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.BOOK_NATURE);
    }

    @Override
    public MessageResponse update(UUID id, BookNatureRequest bookNatureRequest) {

        BookNature bookNature = bookNatureRepository.findById(id).orElseThrow();

        bookNature.setNature(bookNatureRequest.getNature());
        bookNature.setPrice(bookNatureRequest.getPrice());
        bookNature.setQuality(bookNatureRequest.getQuality());

        if(bookNature.isDeleted() != bookNatureRequest.isDeleted())
            bookNature.setDeleted(bookNatureRequest.isDeleted());


        Book book = bookRepository.findById(bookNatureRequest.getBookId()).orElseThrow();
        bookNature.setBook(book);

        bookNatureRepository.save(bookNature);

        updatePriceBook(String.valueOf(book.getId()));

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.BOOK_NATURE);
    }

    @Override
    public MessageResponse delete(UUID id) {

        BookNature bookNature = bookNatureRepository.findById(id).orElseThrow();

        if(!bookNature.isDeleted()) {
            bookNature.setDeleted(true);
            bookNatureRepository.save(bookNature);
        }

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.BOOK_NATURE);
    }

    @Override
    public List<BookNature> findAllByBookId(UUID id) {

        List<BookNature> bookNatureList = bookNatureRepository.findByBook_Id(id).stream().toList();

        return bookNatureList;
    }

    @Override
    public BookNature findById(UUID id) {

        BookNature bookNature = bookNatureRepository.findById(id).orElseThrow();

        return bookNature;
    }


    @Override
    public PriceBook findMinMaxPricesByBookId(String bookId){

        PriceBook priceBook = bookNatureRepository.findMinMaxPricesByBookId(UUID.fromString(bookId)).orElseThrow();

        return priceBook;
    }

    public void updatePriceBook(String bookId){

        Book bookUpdate = bookRepository.findById(UUID.fromString(bookId)).orElseThrow();

        PriceBook priceBook = findMinMaxPricesByBookId(bookId);
        if(priceBook.getPriceMin() == null || priceBook.getPriceMax() == null)
            return;

        bookUpdate.setPriceMin(priceBook.getPriceMin());
        bookUpdate.setPriceMax(priceBook.getPriceMax());

        bookRepository.save(bookUpdate);

    }

}
