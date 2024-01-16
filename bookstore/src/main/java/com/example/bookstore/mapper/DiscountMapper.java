package com.example.bookstore.mapper;

import com.example.bookstore.model.discount.Discount;
import com.example.bookstore.payload.Discount.DiscountRequest;
import com.example.bookstore.payload.Discount.DiscountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    DiscountResponse toDTO(Discount discount);

    Discount toEntity(DiscountRequest discountRequest);

}
