package com.example.bookstore.repository;

import com.example.bookstore.model.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {


    @Query("SELECT b FROM BookNature bn " +
            "JOIN bn.book b " +
            "JOIN b.topic t " +
            "WHERE (:name IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "OR (:topicName IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :topicName, '%'))) " +
            "OR (:rating IS NULL OR b.rating = :rating) " +
            "AND (:priceRange IS NULL OR " +
            "    (:priceRange = '0-150000' AND bn.price <= 150000) OR " +
            "    (:priceRange = '150000-300000' AND bn.price > 150000 AND bn.price <= 300000) OR " +
            "    (:priceRange = '300000-500000' AND bn.price > 300000 AND bn.price <= 500000) OR " +
            "    (:priceRange = '500000-700000' AND bn.price > 500000 AND bn.price <= 700000) OR " +
            "    (:priceRange = '700000-high' AND bn.price > 700000)) " +
            "ORDER BY " +
            "CASE WHEN :sortOption = 'descRating' THEN b.rating END DESC, " +
            "CASE WHEN :sortOption = 'ascName' THEN b.name END ASC")
    List<Book> filterAndSortByCriteria(
            @Param("name") String name,
            @Param("topicName") String topicName,
            @Param("rating") int rating,
            @Param("priceRange") String priceRange,
            @Param("sortOption") String sortOption
    );

    List<Book> findAll(Specification<Book> specification);


    @Query("SELECT DISTINCT b.author FROM Book b WHERE b.author != 'update'")
    List<String> findDistinctAuthors();


}
