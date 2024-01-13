package com.example.bookstore.service;

import com.example.bookstore.payload.TopicResponse;

import java.util.List;

public interface TopicService {

    void create (String name);

    List<TopicResponse> findAll();
}
