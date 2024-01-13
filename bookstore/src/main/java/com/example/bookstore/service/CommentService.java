package com.example.bookstore.service;

import com.example.bookstore.model.Comment;
import com.example.bookstore.payload.CommentRequest;
import com.example.bookstore.payload.MessageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    MessageResponse create(MultipartFile file, CommentRequest commentRequest);

    MessageResponse update(long id, MultipartFile file, CommentRequest commentRequest);

    MessageResponse delete(long id);

    List<Comment> findByBookId(UUID id);

    Comment findById(long id);


}
