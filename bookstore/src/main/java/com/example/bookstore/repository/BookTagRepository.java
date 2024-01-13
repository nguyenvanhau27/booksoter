package com.example.bookstore.repository;

import com.example.bookstore.model.book.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookTagRepository extends JpaRepository<BookTag, Integer> {


    @Query("SELECT COUNT(bt) > 0 FROM BookTag bt WHERE bt.tag.id = :tag AND bt.book.id = :book")
    boolean existsByTagAndBook(@Param("tag") int tagId, @Param("book") UUID bookId);


    Optional<BookTag> findByBook_IdAndTag_Id(UUID bookId, int tagId);

    List<BookTag> findByBook_Id(UUID bookId);

    List<BookTag> findByTag_Id( int tagId);

}
