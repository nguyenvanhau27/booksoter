package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.model.discount.Discount;
import com.example.bookstore.model.discount.DiscountOfUser;
import com.example.bookstore.model.user.User;
import com.example.bookstore.payload.Discount.DiscountOfUserRequest;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.repository.DiscountOfUserRepository;
import com.example.bookstore.repository.DiscountRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.DiscountOfUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DiscountOfUserServiceImpl implements DiscountOfUserService {

    @Autowired
    private DiscountOfUserRepository discountOfUserRepository;
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public MessageResponse create(DiscountOfUserRequest discountOfUserRequest) {

        DiscountOfUser discountOfUser = new DiscountOfUser();

        Discount discount = discountRepository.findById(discountOfUserRequest.getDiscountId()).orElseThrow();
        discountOfUser.setDiscount(discount);

        User user = userRepository.findById(UUID.fromString(discountOfUserRequest.getUserId())).orElseThrow();
        discountOfUser.setUser(user);

        discountOfUserRepository.save(discountOfUser);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT_OF_USER);
    }

    @Override
    public MessageResponse update(long id, DiscountOfUserRequest discountOfUserRequest) {

        DiscountOfUser discountOfUserOld = discountOfUserRepository.findById(id).orElseThrow();

        Discount discount = discountRepository.findById(discountOfUserRequest.getDiscountId()).orElseThrow();
        discountOfUserOld.setDiscount(discount);

        User user = userRepository.findById(UUID.fromString(discountOfUserRequest.getUserId())).orElseThrow();
        discountOfUserOld.setUser(user);

        discountOfUserRepository.save(discountOfUserOld);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT_OF_USER);
    }

    @Override
    public MessageResponse delete(long id) {

        discountOfUserRepository.deleteById(id);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.DISCOUNT_OF_USER);

    }

    @Override
    public DiscountOfUser findById(long id) {

        DiscountOfUser discountOfUser = discountOfUserRepository.findById(id).orElseThrow();

        return discountOfUser;
    }
}
