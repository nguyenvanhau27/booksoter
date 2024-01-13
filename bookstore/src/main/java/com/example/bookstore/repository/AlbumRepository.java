package com.example.bookstore.repository;

import com.example.bookstore.model.book.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT a FROM Album a WHERE a.book.id = :bookId " +
            "AND LOWER(a.name) LIKE LOWER(CONCAT('%', 's1', '%'))")
    Optional<Album> findByNameLikeOrFallback(@Param("bookId") UUID bookId);
}
