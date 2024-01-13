package com.example.bookstore.model.discount;

import com.example.bookstore.model.discount.Discount;
import com.example.bookstore.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discount_of_user")
public class DiscountOfUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_id", nullable = false)
    private Discount discount;

    @Temporal(TemporalType.DATE)
    private Date date;
}
