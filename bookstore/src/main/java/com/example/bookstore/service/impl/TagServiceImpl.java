package com.example.bookstore.service.impl;

import com.example.bookstore.model.book.Tag;
import com.example.bookstore.repository.TagRepository;
import com.example.bookstore.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void create(String name) {
        if(tagRepository.existsByName(name)){
            return;
        }
        Tag tag = new Tag();
        tag.setName(name);

        tagRepository.save(tag);

    }
}
