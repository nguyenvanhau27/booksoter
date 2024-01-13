package com.example.bookstore.repository;

import com.example.bookstore.model.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long> {


    List<Discount> findByType(String type);

    @Query("SELECT d FROM Discount d WHERE d.isDelete = false")
    List<Discount> findByIsDeleteNot();
}
