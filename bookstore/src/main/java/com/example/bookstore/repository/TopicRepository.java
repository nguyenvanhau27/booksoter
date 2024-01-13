package com.example.bookstore.repository;

import com.example.bookstore.model.book.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    boolean existsByName(String name);

    Optional<Topic> findByName(String name);
}
