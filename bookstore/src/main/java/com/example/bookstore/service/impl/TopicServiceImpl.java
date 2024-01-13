package com.example.bookstore.service.impl;

import com.example.bookstore.mapper.TopicMapper;
import com.example.bookstore.model.book.Topic;
import com.example.bookstore.model.enumm.TopicEnum;
import com.example.bookstore.payload.TopicResponse;
import com.example.bookstore.repository.TopicRepository;
import com.example.bookstore.service.TopicService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public void create(String name) {
            if(topicRepository.existsByName(name)){
                return;
            }
            Topic topic = new Topic();
            topic.setName(name);

            topicRepository.save(topic);
    }

    @Override
    public List<TopicResponse> findAll() {

        return topicRepository.findAll().stream().map(t -> this.topicMapper.toDTO(t))
                .collect(Collectors.toList());
    }


//    @PostConstruct
//    public void init() {
//        List<Topic> rolesToSave = new ArrayList<>();
//        for (TopicEnum eRole : TopicEnum.values()) {
//            if (!topicRepository.existsByName(eRole.toString())) {
//                Topic role = new Topic();
//                role.setName(eRole.toString());
//                rolesToSave.add(role);
//            }
//        }
//
//        if (!rolesToSave.isEmpty()) {
//            topicRepository.saveAll(rolesToSave);
//        }
//    }
}
