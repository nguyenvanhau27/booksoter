package com.example.bookstore.controller;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.CommentRequest;
import com.example.bookstore.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping(ApiURL.COMMENT)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> create (@RequestPart(name = "commentRequest") CommentRequest commentRequest,
                                      @RequestPart(name = "file", required = false) MultipartFile file){
        return ResponseEntity.ok(commentService.create(file, commentRequest));
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> update (@PathVariable long id,
                                      @RequestPart(name = "commentRequest") CommentRequest commentRequest,
                                      @RequestPart(name = "file", required = false) MultipartFile file){
        return ResponseEntity.ok(commentService.update(id, file, commentRequest));
    }


    @DeleteMapping(value = "/{id}")
    private ResponseEntity<?> delete (@PathVariable long id){
        return ResponseEntity.ok(commentService.delete(id));
    }


    @GetMapping(value = "/findByBookId/{id}")
    private ResponseEntity<?> findByBookId (@PathVariable UUID id){
        return ResponseEntity.ok(commentService.findByBookId(id));
    }


    @GetMapping(value = "/{id}")
    private ResponseEntity<?> findById (@PathVariable long id){
        return ResponseEntity.ok(commentService.findById(id));
    }

}
