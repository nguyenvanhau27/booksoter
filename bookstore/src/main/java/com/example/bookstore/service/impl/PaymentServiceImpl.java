package com.example.bookstore.service.impl;

import com.example.bookstore.model.Payment;
import com.example.bookstore.repository.PaymentRepository;
import com.example.bookstore.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}
