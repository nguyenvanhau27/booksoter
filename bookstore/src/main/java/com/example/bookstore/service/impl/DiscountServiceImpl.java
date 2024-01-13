package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.model.discount.Discount;
import com.example.bookstore.payload.Discount.DiscountRequest;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.repository.DiscountRepository;
import com.example.bookstore.service.DiscountService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public MessageResponse create(DiscountRequest discountRequest) {

        Discount discount = new Discount();

        discount.setName(discountRequest.getName());
        discount.setType(discountRequest.getType());
        discount.setPriceApplicable(discountRequest.getPriceApplicable());
        discount.setPercent(discountRequest.getPercent());
        discount.setTotalDecrease(discountRequest.getTotalDecrease());

        discount.setStart(discountRequest.getStart());
        discount.setEnd(discountRequest.getEnd());

        discountRepository.save(discount);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT);
    }

    @Override
    public MessageResponse update(long id, DiscountRequest discountRequest) {

        Discount discountOld = discountRepository.findById(id).orElseThrow();

        discountOld.setName(discountRequest.getName());
        discountOld.setType(discountRequest.getType());
        discountOld.setPriceApplicable(discountRequest.getPriceApplicable());
        discountOld.setPercent(discountRequest.getPercent());
        discountOld.setTotalDecrease(discountRequest.getTotalDecrease());

        discountOld.setStart(discountRequest.getStart());
        discountOld.setEnd(discountRequest.getEnd());

        discountRepository.save(discountOld);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT);
    }

    @Override
    public MessageResponse delete(long id) {

        Discount discount = discountRepository.findById(id).orElseThrow();

        if(!discount.isDelete())
            discount.setDelete(true);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT);
    }

    @Override
    public List<Discount> findByType(String type) {

        List<Discount> discountList = discountRepository.findByType(type).stream().toList();

        return discountList;
    }

    @Override
    public List<Discount> findByIsDeleteNot() {

        List<Discount> discountList = discountRepository.findByIsDeleteNot().stream().toList();

        return discountList;
    }

    @Override
    public List<Discount> findAll() {

        List<Discount> discountList = discountRepository.findAll().stream().toList();

        return discountList;
    }

    @Override
    public Discount findById(long id) {

        Discount discount = discountRepository.findById(id).orElseThrow();

        return discount;
    }
}
