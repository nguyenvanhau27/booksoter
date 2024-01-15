package com.example.bookstore.controller.old;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.book.BookTagRequest;
import com.example.bookstore.service.BookTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURL.BOOK_TAG)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookTagController {

    @Autowired
    private BookTagService bookTagService;

    @PostMapping(value = "")
    private ResponseEntity<?> create(@RequestBody BookTagRequest bookTagRequest){
        return ResponseEntity.ok(bookTagService.create(bookTagRequest));
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<?> update(@PathVariable int id,
                                     @RequestBody BookTagRequest bookTagRequest){
        return ResponseEntity.ok(bookTagService.update(id,bookTagRequest));
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<?> delete(@PathVariable int id){
        return ResponseEntity.ok(bookTagService.delete(id));
    }


    @GetMapping(value = "/{id}")
    private ResponseEntity<?> findById(@PathVariable int id){
        return ResponseEntity.ok(bookTagService.findById(id));
    }

    @GetMapping(value = "/{tagId}")
    private ResponseEntity<?> findByTagId(@PathVariable int tagId){
        return ResponseEntity.ok(bookTagService.findByTagId(tagId));
    }

    @GetMapping(value = "/{bookId}")
    private ResponseEntity<?> findByBookId(@PathVariable String bookId){
        return ResponseEntity.ok(bookTagService.findByBookId(bookId));
    }

    @GetMapping(value = "/findByBookIdAndTagId")
    private ResponseEntity<?> findByBookIdAndTagId(@RequestBody BookTagRequest bookTagRequest){
        return ResponseEntity.ok(bookTagService.findByBookIdAndTagId(bookTagRequest));
    }




}
