package com.example.bookstore.service.impl;


import com.example.bookstore.config.JwtServiceImpl;
import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.mapper.UserMapper;
import com.example.bookstore.model.role.Role;
import com.example.bookstore.model.enumm.RoleNameEnum;
import com.example.bookstore.model.user.User;
import com.example.bookstore.payload.auth.JwtAuthenticationResponse;
import com.example.bookstore.payload.MessageResponse;
import com.example.bookstore.payload.auth.SigninRequest;
import com.example.bookstore.payload.auth.SignupRequest;
import com.example.bookstore.payload.user.UserResponse;
import com.example.bookstore.repository.RoleRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponse signup(SignupRequest signupRequest) throws Exception {
        User user = userMapper.toEntitySignup(signupRequest);

//        user.setName(signupRequest.getName());
//        user.setEmail(signupRequest.getEmail());
        if(userRepository.existsByEmail(user.getEmail()))
            throw new Exception("email exists");

        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        Role roleAdmin = roleRepository.findByName(RoleNameEnum.ROLE_ADMIN.name()).orElseThrow(() ->
                new IllegalArgumentException("exists role"));

//        Role roleCustomer = roleRepository.findById(ERole.customer).orElseThrow(() ->
//                new IllegalArgumentException("exists role"));

        user.setRole(roleAdmin);

        UserResponse userResponse = userMapper.toDTO(userRepository.save(user));

        return userResponse;
    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
                signinRequest.getPassword()));

        var user =  userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() ->
                new IllegalArgumentException("Invalid email"));

        user.build(user);


        var jwt = jwtService.generateToken(authentication, user);

        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        UserResponse userResponse = this.userMapper.toDTO(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setUserResponse(userResponse);
        return jwtAuthenticationResponse;

    }

    @Override
    public MessageResponse changePassword(String newPassword) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);


        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.AUTH);
    }
}
