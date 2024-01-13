package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.mapper.AddressMapper;
import com.example.bookstore.model.user.Address;
import com.example.bookstore.model.user.User;
import com.example.bookstore.payload.address.AddressRequest;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.repository.AddressRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.AddressService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public MessageResponse create(AddressRequest addressRequest) {

//        Address address = new Address();
//        address.setType(addressRequest.getType());
//        address.setLocation(addressRequest.getLocation());
//
//        User user = userRepository.findById(UUID.fromString(addressRequest.getUserId())).orElseThrow();

        Address address = addressMapper.toEntity(addressRequest);

//        address.setUser(user);

        boolean flag = addressRepository.existsByLocationAndTypeAndUser_Id(
                address.getLocation(),
                address.getType(),
                address.getUser().getId());

        if(flag){
            //throw
        }
        addressRepository.save(address);


        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.ADDRESS);
    }

    @Override
    public MessageResponse update(int id, AddressRequest addressRequest) {

        Address addressOld = addressRepository.findById(id).orElseThrow();

        addressOld.setType(addressRequest.getType());
        addressOld.setLocation(addressRequest.getLocation());

        addressRepository.save(addressOld);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.ADDRESS);
    }

    @Override
    public MessageResponse delete(int id) {

        addressRepository.deleteById(id);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.ADDRESS);
    }

    @Override
    public List<Address> findByUserId(String userId) {

        List<Address> addressList = addressRepository.findByUser_Id(UUID.fromString(userId)).stream().toList();

        return addressList;
    }

    @Override
    public Address findById(int id) {

        Address address = addressRepository.findById(id).orElseThrow();

        return address;
    }
}
