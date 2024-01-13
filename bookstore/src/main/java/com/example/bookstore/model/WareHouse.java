package com.example.bookstore.model;

import com.example.bookstore.model.book.BookNature;
import com.example.bookstore.model.book.StatusBook;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ware_house")
public class WareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    @Column(name = "book_code")
    private String bookCode;

    @Column(name = "raw_price")
    private BigDecimal rawPrice;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    private int quality;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String value;

    private String note;

    private boolean io;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_nature_id", nullable = false)
    private BookNature bookNature;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_book_id", nullable = false)
    private StatusBook statusBook;
}
