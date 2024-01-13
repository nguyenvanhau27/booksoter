package com.example.bookstore.repository;

import com.example.bookstore.model.book.Tag;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

     boolean existsByName(String name);
}
