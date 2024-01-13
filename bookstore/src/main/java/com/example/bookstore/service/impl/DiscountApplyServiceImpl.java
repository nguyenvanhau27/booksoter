package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.model.OrderDetail;
import com.example.bookstore.model.discount.Discount;
import com.example.bookstore.model.discount.DiscountApply;
import com.example.bookstore.payload.Discount.DiscountApplyRequest;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.repository.DiscountApplyRepository;
import com.example.bookstore.repository.DiscountRepository;
import com.example.bookstore.repository.OrderDetailRepository;
import com.example.bookstore.service.DiscountApplyService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DiscountApplyServiceImpl implements DiscountApplyService {

    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private DiscountApplyRepository discountApplyRepository;


    @Override
    public MessageResponse create(DiscountApplyRequest discountApplyRequest) {

        DiscountApply discountApply = new DiscountApply();

        Discount discount = discountRepository.findById(discountApplyRequest.getDiscountId()).orElseThrow();

        discountApply.setDiscount(discount);

        OrderDetail orderDetail = orderDetailRepository.
                findById(UUID.fromString(discountApplyRequest.getOrderDetailId())).orElseThrow();

        discountApply.setOrderDetail(orderDetail);

        discountApplyRepository.save(discountApply);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT_APPLY);
    }

    @Override
    public MessageResponse update(long id, DiscountApplyRequest discountApplyRequest) {

        DiscountApply discountApplyOld = discountApplyRepository.findById(id).orElseThrow();

        //check id discount and id order detail


        Discount discount = discountRepository.findById(discountApplyRequest.getDiscountId()).orElseThrow();

        discountApplyOld.setDiscount(discount);

        OrderDetail orderDetail = orderDetailRepository.
                findById(UUID.fromString(discountApplyRequest.getOrderDetailId())).orElseThrow();

        discountApplyOld.setOrderDetail(orderDetail);

       discountApplyRepository.save(discountApplyOld);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT_APPLY);

    }

    @Override
    public MessageResponse delete(long id) {

        discountApplyRepository.deleteById(id);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT_APPLY);

    }

    @Override
    public DiscountApply findById(long id) {

        DiscountApply discountApply = discountApplyRepository.findById(id).orElseThrow();

        return discountApply;
    }
}
