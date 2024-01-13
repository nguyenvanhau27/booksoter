package com.example.bookstore.repository;

import com.example.bookstore.model.discount.Discount;
import com.example.bookstore.model.discount.DiscountApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountApplyRepository extends JpaRepository<DiscountApply, Long> {

}
