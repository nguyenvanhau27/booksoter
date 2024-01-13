package com.example.bookstore.payload;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class WareHouseRequest {

    private String author;
    private String bookCode;
    private BigDecimal rawPrice;
    private BigDecimal salePrice;
    private int quality;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String value;
    private String note;
    private boolean io;

    private String bookNatureId;
    private int statusBookId;

}
