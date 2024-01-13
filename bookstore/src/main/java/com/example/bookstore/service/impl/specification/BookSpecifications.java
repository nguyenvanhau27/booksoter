package com.example.bookstore.service.impl.specification;

import com.example.bookstore.model.book.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookNature;
import com.example.bookstore.model.book.Topic;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Date;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookNature;
import com.example.bookstore.model.book.Topic;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> filterAndSortByCriteria(
            String name,
            String topicName,
            int rating,
            String priceRange,
            String sortOption,
            String author,
            String status
    ) {
        return (Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (name != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (author != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
            }

            if (topicName != null) {
                Join<Book, Topic> topicJoin = root.join("topic");
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(topicJoin.get("name")), "%" + topicName.toLowerCase() + "%"));
            }

            if (rating > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("rating"), rating));
            }

            if (priceRange != null) {
                predicate = criteriaBuilder.and(predicate, getPriceRangePredicate(root, criteriaBuilder, priceRange));
            }

            if (status != null) {
                Join<Book, StatusBook> statusJoin = root.join("status", JoinType.INNER);
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(statusJoin.get("name")), "%" + status.toLowerCase() + "%"));
            }


            query.orderBy(
                    criteriaBuilder.desc(criteriaBuilder.selectCase()
                            .when(criteriaBuilder.equal(criteriaBuilder.literal(sortOption), criteriaBuilder.literal("descRating")), root.get("rating"))
                            .otherwise(0) // Provide a default value when not sorting by rating
                    ),
                    criteriaBuilder.asc(criteriaBuilder.selectCase()
                            .when(criteriaBuilder.equal(criteriaBuilder.literal(sortOption), criteriaBuilder.literal("ascName")), root.get("name"))
                            .otherwise("") // Provide a default value when not sorting by name
                    )
            );

            return predicate;
        };
    }

    private static Predicate getPriceRangePredicate(Root<Book> root, CriteriaBuilder criteriaBuilder, String priceRange) {
        if ("all".equalsIgnoreCase(priceRange)) {
            // Case 3: Show all prices without specific filtering
            return criteriaBuilder.and();  // Empty predicate to indicate no price filtering
        } else {
            // Continue with the existing logic for specific price range filtering

            String[] priceRangeParts = priceRange.split("-");
            BigDecimal min = new BigDecimal(priceRangeParts[0]);
            BigDecimal max = new BigDecimal(priceRangeParts[1]);

            if (min.compareTo(max) == 0) {
                // Case 1: min and max are equal, check against specific range
                return criteriaBuilder.and(criteriaBuilder.equal(root.get("priceMin"), min));
            } else {
                // Case 2: min and max are different, check against a range
                return criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("priceMin"), min),
                        criteriaBuilder.lessThanOrEqualTo(root.get("priceMax"), max)
                );
            }
        }
    }

}


//public class BookSpecifications {
//
//    public static Specification<Book> filterAndSortByCriteria(
//            String name,
//            String topicName,
//            int rating,
//            String priceRange,
//            String sortOption
//    ) {
//        return (Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
//            Predicate predicate = criteriaBuilder.conjunction();
//
//            if (name != null) {
//                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
//            }
//
//            if (topicName != null) {
//                Join<Book, Topic> topicJoin = root.join("topic");
//                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(topicJoin.get("name")), "%" + topicName.toLowerCase() + "%"));
//            }
//
//            if (rating > 0) {
//                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("rating"), rating));
//            }
//
//            if (priceRange != null) {
//                Join<Book, BookNature> bookNatureJoin = root.join("bookNatures", JoinType.LEFT);
//                predicate = criteriaBuilder.and(predicate, getPriceRangePredicate(bookNatureJoin, criteriaBuilder, priceRange));
//            }
//
//            query.orderBy(
//                    criteriaBuilder.desc(criteriaBuilder.selectCase()
//                            .when(criteriaBuilder.equal(criteriaBuilder.literal(sortOption), criteriaBuilder.literal("descRating")), root.get("rating"))
//                            .otherwise(0) // Provide a default value when not sorting by rating
//                    ),
//                    criteriaBuilder.asc(criteriaBuilder.selectCase()
//                            .when(criteriaBuilder.equal(criteriaBuilder.literal(sortOption), criteriaBuilder.literal("ascName")), root.get("name"))
//                            .otherwise("") // Provide a default value when not sorting by name
//                    )
//            );
//
//            return predicate;
//        };
//    }
//
//    private static Predicate getPriceRangePredicate(Join<Book, BookNature> bookNatureJoin, CriteriaBuilder criteriaBuilder, String priceRange) {
//        switch (priceRange) {
//            case "0-150000":
//                return criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(bookNatureJoin.get("price"), BigDecimal.valueOf(150000)));
//            case "150000-300000":
//                return criteriaBuilder.and(
//                        criteriaBuilder.greaterThan(bookNatureJoin.get("price"), BigDecimal.valueOf(150000)),
//                        criteriaBuilder.lessThanOrEqualTo(bookNatureJoin.get("price"), BigDecimal.valueOf(300000))
//                );
//            case "300000-500000":
//                return criteriaBuilder.and(
//                        criteriaBuilder.greaterThan(bookNatureJoin.get("price"), BigDecimal.valueOf(300000)),
//                        criteriaBuilder.lessThanOrEqualTo(bookNatureJoin.get("price"), BigDecimal.valueOf(500000))
//                );
//            case "500000-700000":
//                return criteriaBuilder.and(
//                        criteriaBuilder.greaterThan(bookNatureJoin.get("price"), BigDecimal.valueOf(500000)),
//                        criteriaBuilder.lessThanOrEqualTo(bookNatureJoin.get("price"), BigDecimal.valueOf(700000))
//                );
//            case "700000-high":
//                return criteriaBuilder.and(criteriaBuilder.greaterThan(bookNatureJoin.get("price"), BigDecimal.valueOf(700000)));
//            default:
//                return null; // You can modify this based on your specific requirements
//        }
//    }
//}




