package com.example.bookstore.mapper;

import com.example.bookstore.model.user.User;
import com.example.bookstore.payload.auth.SignupRequest;
import com.example.bookstore.payload.user.UserRequest;
import com.example.bookstore.payload.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    @Mapping(source = "role.name", target = "roleName")
    UserResponse toDTO(User user);
    User toEntity(UserRequest userRequest);
    User toEntitySignup(SignupRequest signupRequest);
}
