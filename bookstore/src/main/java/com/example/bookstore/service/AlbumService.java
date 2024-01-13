package com.example.bookstore.service;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.payload.MessageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface AlbumService {

//    MessageResponse create(MultipartFile file, UUID bookId);
    MessageResponse create(List<MultipartFile> file, UUID bookId);
//    MessageResponse create(MultipartFile file);
//    MessageResponse create(UUID bookId);
}
