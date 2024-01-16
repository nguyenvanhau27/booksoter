package com.example.bookstore.repository;

import com.example.bookstore.model.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long> {


    @Query("SELECT d FROM Discount d WHERE LOWER(d.type) LIKE LOWER(CONCAT('%', :type, '%'))")
    List<Discount> findByType(String type);
    @Query("SELECT d FROM Discount d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Discount> findByName(String name);

    @Query("SELECT d FROM Discount d WHERE d.isDelete = false")
    List<Discount> findByIsDeleteNot();


}
