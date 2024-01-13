package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.model.Comment;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.user.User;
import com.example.bookstore.payload.CommentRequest;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CommentRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.CommentService;
import com.example.bookstore.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public MessageResponse create(MultipartFile file, CommentRequest commentRequest) {

        Comment comment = new Comment();

        comment.setRating(commentRequest.getRating());

        if(commentRequest.getLikes() != null )
            comment.setLikes(commentRequest.getLikes());

        Book book = bookRepository.findById(UUID.fromString(commentRequest.getBookId())).orElseThrow();

        comment.setBook(book);

        User user = userRepository.findById(UUID.fromString(commentRequest.getUserId())).orElseThrow();

        comment.setUser(user);
        comment.setDate(new Date());
        comment.setCommentContent(commentRequest.getCommentContent());

        if(file != null && !file.isEmpty()){
            String imageComment = fileService.uploadFile(file,false);
            comment.setImage(imageComment);
        }

        commentRepository.save(comment);


        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.COMMENT);

    }

    @Override
    public MessageResponse update(long id, MultipartFile file, CommentRequest commentRequest) {

        Comment commentOld = commentRepository.findById(id).orElseThrow();

        commentOld.setCommentContent(commentRequest.getCommentContent());
        commentOld.setRating(commentRequest.getRating());
        commentOld.setDate(new Date());

        if(commentRequest.getLikes() != null )
            commentOld.setLikes(commentRequest.getLikes());

        if(file != null && !file.isEmpty()){
            String imageComment = fileService.uploadFile(file,false);

            fileService.deleteFile(commentOld.getImage(), false);

            commentOld.setImage(imageComment);
        }

        commentRepository.save(commentOld);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.COMMENT);

    }

    @Override
    public MessageResponse delete(long id) {

        Comment commentDelete = commentRepository.findById(id).orElseThrow();

        fileService.deleteFile(commentDelete.getImage(), false);

        commentRepository.delete(commentDelete);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.COMMENT);
    }

    @Override
    public List<Comment> findByBookId(UUID id) {

        List<Comment> commentList = commentRepository.findByBook_Id(id).stream().toList();

        return commentList;
    }

    @Override
    public Comment findById(long id) {

        Comment comment = commentRepository.findById(id).orElseThrow();

        return null;
    }
}
