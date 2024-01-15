package com.example.bookstore.controller.old;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiURL.TOPIC)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicController {

    @Autowired
    private TopicService topicService;


    @GetMapping("/")
    public ResponseEntity<?> findByAll() {
        return ResponseEntity.ok(topicService.findAll());
    }
}
