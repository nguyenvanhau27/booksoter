package com.example.bookstore.repository;

import com.example.bookstore.model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {


    List<WareHouse> findByBookNature_Id(UUID bookNatureIdd);

//    List<WareHouse> findByBookNature_Id(UUID bookNatureIdd);

}
