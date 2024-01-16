package com.example.bookstore.controller;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.payload.book.FilterSearchBookRequest;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.DiscountService;
import com.example.bookstore.service.FileService;
import com.example.bookstore.service.TopicService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(ApiURL.MATERIAL)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class MaterialController {

    @Autowired
    private BookService bookService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private FileService fileService;
    @Autowired
    private DiscountService discountService;


    @GetMapping("/author")
    public ResponseEntity<?> findAuthor() {
        return ResponseEntity.ok(bookService.findAuthor());
    }

    @GetMapping("/topic")
    public ResponseEntity<?> findByAll() {
        return ResponseEntity.ok(topicService.findAll());
    }

    @GetMapping("/book/filter")
    @PermitAll
    public ResponseEntity<?> filter(@RequestBody FilterSearchBookRequest filterSearchBookRequest){
        return ResponseEntity.ok(bookService.filter(filterSearchBookRequest));
    }


    @GetMapping("/album/display/{fileName}")  // view on web
    public ResponseEntity<byte[]> displayFile(@PathVariable String fileName) throws IOException {
        Resource resource = fileService.downloadFile(fileName);
        String originalFilename = resource.getFilename().split("_", 2)[1];

        Tika tika = new Tika();
        String mediaType = tika.detect(originalFilename);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mediaType));

        headers.setContentDisposition(ContentDisposition.inline().filename(originalFilename).build());

        InputStream inputStream = resource.getInputStream();
        byte[] fileBytes = IOUtils.toByteArray(inputStream);
        inputStream.close();

        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }


    @GetMapping(value = "/discount/{id}")
    private ResponseEntity<?> findById(@PathVariable long id){
        return ResponseEntity.ok(discountService.findById(id));
    }


}
