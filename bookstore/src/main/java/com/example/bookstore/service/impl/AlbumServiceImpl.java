package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.model.book.Album;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.repository.AlbumRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.AlbumService;
import com.example.bookstore.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public MessageResponse create(List<MultipartFile> files, UUID bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow();
        List<Album> albums = new ArrayList<>();

        for(MultipartFile file: files) {

            Album album = new Album();
            String fileName = fileService.uploadFile(file, true);

            album.setName(fileName);
            album.setBook(book);

            albums.add(album);

        }
        albumRepository.saveAll(albums);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.AlBUM);

    }

//    @Override
//    public MessageResponse create(MultipartFile file, UUID bookId) {
//
//        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.AlBUM);
//
//    }
}
