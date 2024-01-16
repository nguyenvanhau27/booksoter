package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.mapper.DiscountMapper;
import com.example.bookstore.model.discount.Discount;
import com.example.bookstore.payload.Discount.DiscountRequest;
import com.example.bookstore.payload.Discount.DiscountResponse;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.repository.DiscountRepository;
import com.example.bookstore.service.DiscountService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private DiscountMapper discountMapper;


    @Override
    public MessageResponse create(DiscountRequest discountRequest) {

        Discount discount = discountMapper.toEntity(discountRequest);

        discountRepository.save(discount);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT);
    }

    @Override
    public MessageResponse update(long id, DiscountRequest discountRequest) {

        Discount discountOld = discountRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found: " + id));

        Discount discountUpdate = discountMapper.toEntity(discountRequest);
        discountUpdate.setId(discountOld.getId());

        discountRepository.save(discountUpdate);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT);
    }

    @Override
    public MessageResponse delete(long id) {

        Discount discount = discountRepository.findById(id).orElseThrow();

        if (!discount.isDelete())
            discount.setDelete(true);

        discountRepository.save(discount);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT);
    }

    @Override
    public List<DiscountResponse> findByType(String type) {
        List<Discount> discount = discountRepository.findByType(type);

        if (discount.isEmpty()) {
            throw new NotFoundException("Not found" + type);
        }

        List<DiscountResponse> discountList = discountRepository.findByType(type).stream().
                map(item -> discountMapper.toDTO(item)).collect(Collectors.toList());

        return discountList;
    }

    @Override
    public List<DiscountResponse> findByName(String name) {
        List<Discount> discount = discountRepository.findByName(name);

        if (discount.isEmpty()) {
            throw new NotFoundException("Not found" + name);
        }

        List<DiscountResponse> discountList = discountRepository.findByName(name).stream().
                map(item ->discountMapper.toDTO(item)).collect(Collectors.toList());
        return discountList;
    }

    @Override
    public List<DiscountResponse> findByIsDeleteNot() {
        List<Discount> discount = discountRepository.findByIsDeleteNot();

        if (discount.isEmpty()) {
            throw new NotFoundException("Not found: discount deleted");
        }

        List<DiscountResponse> discountList = discountRepository.findByIsDeleteNot().stream().
                map(item -> discountMapper.toDTO(item)).collect(Collectors.toList());

        return discountList;
    }

    @Override
    public List<DiscountResponse> findAll() {

        List<DiscountResponse> discountList = discountRepository.findByIsDeleteNot().stream().
                map(item -> discountMapper.toDTO(item)).collect(Collectors.toList());

        return discountList;
    }

    @Override
    public DiscountResponse findById(long id) {

        DiscountResponse discountResponse = this.discountMapper.toDTO(discountRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found: " + id)));

        return discountResponse;
    }
}
