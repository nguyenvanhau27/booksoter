package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderDetail;
import com.example.bookstore.model.book.BookNature;
import com.example.bookstore.model.book.StatusBook;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.order.OrderDetailRequest;
import com.example.bookstore.repository.BookNatureRepository;
import com.example.bookstore.repository.OrderDetailRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.StatusRepository;
import com.example.bookstore.service.OrderDetailService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookNatureRepository bookNatureRepository;
    @Autowired
    private StatusRepository statusRepository;

    private final int paid = 5;
    private final int unpaid = 6;


    @Override
    public MessageResponse create(long orderId, OrderDetailRequest orderDetailRequest) {

        Order order = orderRepository.findById(orderId).orElseThrow();

        BookNature bookNature = bookNatureRepository.findById(UUID.fromString(orderDetailRequest.getBookNatureID()))
                .orElseThrow();

        //check status order
        StatusBook status;
        if(order.getPayment().getName().equalsIgnoreCase("pay later"))
            status = statusRepository.findById(unpaid).orElseThrow();
        else
            status = statusRepository.findById(paid).orElseThrow();

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrder(order);
        orderDetail.setBookNature(bookNature);
        orderDetail.setStatus(status);
        orderDetail.setQuality(orderDetailRequest.getQuality());
        orderDetail.setDate(new Date());

        orderDetailRepository.save(orderDetail);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.ORDER_DETAIL);
    }
}
