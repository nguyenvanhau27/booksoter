package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.Payment;
import com.example.bookstore.model.user.Address;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.order.OrderRequest;
import com.example.bookstore.repository.AddressRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.PaymentRepository;
import com.example.bookstore.service.OrderDetailService;
import com.example.bookstore.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderDetailService orderDetailService;


    @Override
    public MessageResponse create(OrderRequest orderRequest) {

        //create order
        Order order = new Order();

        Address address = addressRepository.findById(orderRequest.getAddressId()).orElseThrow();

        //momo, zalo, payed
        Payment payment = paymentRepository.findById(orderRequest.getPaymentId()).orElseThrow();

        order.setAddress(address);
        order.setPayment(payment);
        order.setDate(new Date());

        Order orderNew = orderRepository.save(order);

        //loop orderDetail
        int loop = orderRequest.getOrderDetailRequestList().size();
        for (int i = 0; i < loop; i++) {
            orderDetailService.create(orderNew.getId(),orderRequest.getOrderDetailRequestList().get(i));
        }


        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.ORDER);
    }

    @Override
    public MessageResponse update(long id, OrderRequest orderRequest) {

        //update address
        //update payment from paid later to zalo or momo

        return null;
    }

    @Override
    public MessageResponse delete(long id) {
        return null;
    }
}
