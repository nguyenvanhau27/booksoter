package com.example.bookstore.payload.Discount;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountResponse {

    private long id;
    private String name;
    private Float percent;
    private Float totalDecrease;
    private Float priceApplicable;
    private String type;
    @Temporal(TemporalType.DATE)
    private Date start;
    @Temporal(TemporalType.DATE)
    private Date end;

    private boolean isDelete;
}
