package com.example.bookstore.model.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "count_paied", columnDefinition = "int default 0")
    private int countPaied;

    @Column(name = "likes", columnDefinition = "int default 0")
    private int likes; //check

    @Column(name = "rating", columnDefinition = "int default 0")
    private int rating;

    @Column(name = "author")
    private String author;

    @Column(name = "information")
    private String information;

    @Column(name = "descriptions")
    private String descriptions;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusBook status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    private String supplier;

    @Column(name = "publishing_company")
    private String publishingCompany;

    private String form;

    @Temporal(TemporalType.DATE)
    @Column(name = "publishing_year")
    private Date publishingYear;

    private String language;

    private String weight;

    @Column(name = "packaging_size")
    private String packagingSize;

    @Column(name = "number_of_page")
    private Integer numberOfPage;

    @Column(name = "price_max", nullable = false, columnDefinition = "DECIMAL(19,2) DEFAULT 0.00")
    private BigDecimal priceMax = BigDecimal.ZERO;

    @Column(name = "price_min", nullable = false, columnDefinition = "DECIMAL(19,2) DEFAULT 0.00")
    private BigDecimal priceMin = BigDecimal.ZERO;

    @Column(name = "discount_book", nullable = false, columnDefinition = "bigint default 0")
    private Float discountBook;


}


//    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
//    private List<BookNature> bookNatures;