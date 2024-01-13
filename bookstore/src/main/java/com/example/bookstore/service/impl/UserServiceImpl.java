package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.mapper.UserMapper;
import com.example.bookstore.model.user.User;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.user.UserRequest;
import com.example.bookstore.payload.user.UserResponse;
import com.example.bookstore.repository.RoleRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public List<UserResponse> findAll() {

        List<UserResponse> userList = userRepository.findAll().stream().map(
                u -> this.userMapper.toDTO(u)).collect(Collectors.toList());

        return userList;
    }

    @Override
    public UserResponse findById(UUID id) {
        return this.userMapper.toDTO(userRepository.findById(id).orElseThrow());
    }

    @Override
    public MessageResponse update(UUID id, UserRequest userRequest) {


        User userOld = userRepository.findById(id).orElseThrow();

        User userUpdate = userMapper.toEntity(userRequest);
        userUpdate.setId(userOld.getId());
        userUpdate.setRole(userOld.getRole());

        userRepository.save(userUpdate);


        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.USER);
    }

    @Override
    public Boolean existsEmail(String email) {

        //case changeEmail

        String emailHolder = SecurityContextHolder.getContext().getAuthentication().getName();

        //email equals ->
        if(emailHolder.equalsIgnoreCase(email))
            return true;
        else {
            boolean flag = userRepository.existsByEmail(email); //true -> exists
            return !flag;
        }
    }

}
