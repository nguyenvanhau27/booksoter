package com.example.bookstore.payload.book;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PriceBook {

    private BigDecimal priceMin;
    private BigDecimal priceMax;

    public PriceBook(BigDecimal priceMin, BigDecimal priceMax) {
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }
}
