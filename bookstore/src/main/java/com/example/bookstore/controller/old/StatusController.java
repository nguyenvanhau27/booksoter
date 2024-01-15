package com.example.bookstore.controller.old;

import com.example.bookstore.constant.ApiURL;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiURL.AlBUM)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class StatusController {
}
