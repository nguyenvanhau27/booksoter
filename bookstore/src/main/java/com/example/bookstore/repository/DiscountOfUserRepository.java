package com.example.bookstore.repository;

import com.example.bookstore.model.discount.DiscountOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountOfUserRepository extends JpaRepository<DiscountOfUser,Long> {

}
