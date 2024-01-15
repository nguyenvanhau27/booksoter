package com.example.bookstore.controller.old;


import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.book.BookNatureRequest;
import com.example.bookstore.service.BookNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiURL.BOOK_NATURE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookNatureController {
    @Autowired
    private BookNatureService bookNatureService;

    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody BookNatureRequest bookNatureRequest) {
        return ResponseEntity.ok(bookNatureService.create(bookNatureRequest));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,
            @RequestBody BookNatureRequest bookNatureRequest) {
        return ResponseEntity.ok(bookNatureService.update(id,bookNatureRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(bookNatureService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookNatureService.findById(id));
    }


    @GetMapping("/findAllByIdBook/{id}")
    public ResponseEntity<?> findAll(@PathVariable UUID id) {
        return ResponseEntity.ok(bookNatureService.findAllByBookId(id));
    }

//    @GetMapping("/findMaxAndMin")
//    public ResponseEntity<?> findMaxAndMin(@RequestParam(name = "bookId") String bookId) {
//        return ResponseEntity.ok(bookNatureService.findMinMaxPricesByBookId(bookId));
//    }

}
