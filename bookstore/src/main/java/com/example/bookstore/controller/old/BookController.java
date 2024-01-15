package com.example.bookstore.controller.old;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.book.BookRequest;
import com.example.bookstore.payload.book.FilterSearchBookRequest;
import com.example.bookstore.service.BookService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.BOOK)
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.create(bookRequest));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update (@PathVariable UUID id,
                                     @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.update(id, bookRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.findByBook(id));
    }


    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }


    @GetMapping("/filter")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PermitAll
    public ResponseEntity<?> filter(@RequestBody FilterSearchBookRequest filterSearchBookRequest){
        return ResponseEntity.ok(bookService.filter(filterSearchBookRequest));
    }

}
