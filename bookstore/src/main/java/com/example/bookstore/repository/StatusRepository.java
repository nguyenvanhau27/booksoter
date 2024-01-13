package com.example.bookstore.repository;

import com.example.bookstore.model.book.StatusBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<StatusBook, Integer> {

    Optional<StatusBook> findByName(String name);
}
