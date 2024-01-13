package com.example.bookstore.repository;

import com.example.bookstore.model.book.BookNature;
import com.example.bookstore.payload.book.PriceBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookNatureRepository extends JpaRepository<BookNature, UUID> {

    List<BookNature> findByBook_Id(UUID id);

//    @Query("SELECT bn FROM BookNature bn " +
//            "JOIN bn.book b " +
//            "WHERE (:name IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
//            "AND (:topicId IS NULL OR b.topic.id = :topicId) " +
//            "AND (:rating IS NULL OR b.rating = :rating) " +
//            "AND (:priceRange IS NULL OR " +
//            "    (:priceRange = '0-150000' AND bn.price <= 150000) OR " +
//            "    (:priceRange = '150000-300000' AND bn.price > 150000 AND bn.price <= 300000) OR " +
//            "    (:priceRange = '300000-500000' AND bn.price > 300000 AND bn.price <= 500000) OR " +
//            "    (:priceRange = '500000-700000' AND bn.price > 500000 AND bn.price <= 700000) OR " +
//            "    (:priceRange = '700000-high' AND bn.price > 700000)) " +
//            "ORDER BY " +
//            "CASE WHEN :sortOption = 'descRating' THEN b.rating END DESC, " +
//            "CASE WHEN :sortOption = 'ascName' THEN b.name END ASC")
//    List<BookNature> filterAndSortByCriteria(
//            @Param("name") String name,
//            @Param("topicId") UUID topicId,
//            @Param("rating") int rating,
//            @Param("priceRange") String priceRange,
//            @Param("sortOption") String sortOption
//    );


//    @Query("")
//    PriceBook findPriceBook(UUID bookId);

    @Query("SELECT new com.example.bookstore.payload.book.PriceBook(MIN(bn.price), " +
            "MAX(bn.price)) FROM BookNature bn WHERE bn.book.id = :bookId")
    Optional<PriceBook> findMinMaxPricesByBookId(@Param("bookId") UUID bookId);

}
