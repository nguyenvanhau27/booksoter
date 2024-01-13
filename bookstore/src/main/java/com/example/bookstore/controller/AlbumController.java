package com.example.bookstore.controller;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.service.AlbumService;
import com.example.bookstore.service.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiURL.AlBUM)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private FileService fileService;

    @PostMapping(value = "", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestParam(name = "bookId") UUID bookId,
                                    @RequestPart(name = "files", required = false) List<MultipartFile> files) {
        return ResponseEntity.ok(albumService.create(files, bookId));
    }

    @GetMapping("/display/{fileName}")  // view on web
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



//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable UUID id) {
//        return ResponseEntity.ok(bookService.findByBook(id));
//    }
//
//    @GetMapping("/findAll")
//    public ResponseEntity<?> findAll() {
//        return ResponseEntity.ok(bookService.findAll());
//    }

}
