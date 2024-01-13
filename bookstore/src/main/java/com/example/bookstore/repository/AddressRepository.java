package com.example.bookstore.repository;

import com.example.bookstore.model.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByUser_Id(UUID userId);

    boolean existsByLocationAndTypeAndUser_Id(String location, String type, UUID userId);
}
