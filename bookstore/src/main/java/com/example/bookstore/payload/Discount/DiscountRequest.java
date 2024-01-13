package com.example.bookstore.payload.Discount;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class DiscountRequest {

    private String name;
    private Float percent;
    private Float totalDecrease;
    private Float priceApplicable;
    private String type;
    @Temporal(TemporalType.DATE)
    private Date start;
    @Temporal(TemporalType.DATE)
    private Date end;

    private Boolean isDelete;
}
