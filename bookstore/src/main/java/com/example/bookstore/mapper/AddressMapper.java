package com.example.bookstore.mapper;

import com.example.bookstore.model.user.Address;
import com.example.bookstore.model.user.User;
import com.example.bookstore.payload.address.AddressRequest;
import com.example.bookstore.payload.address.AddressResponse;
import com.example.bookstore.payload.user.UserRequest;
import com.example.bookstore.payload.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface AddressMapper {

//    @Mapping(source = "role.name", target = "roleName")
    AddressResponse toDTO(Address address);

    @Mapping(source = "userId", target = "user.id", qualifiedByName = "stringToUuid")
    Address toEntity(AddressRequest addressRequest);

    @Named("stringToUuid")
    static UUID stringToUuid(String userId) {
        return UUID.fromString(userId);
    }


}
