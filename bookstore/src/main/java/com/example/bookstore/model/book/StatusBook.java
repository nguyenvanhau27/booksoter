package com.example.bookstore.model.book;


import com.example.bookstore.model.enumm.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "status_book")
public class StatusBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Enumerated(EnumType.STRING)
//    @NaturalId
    private String name;
}
