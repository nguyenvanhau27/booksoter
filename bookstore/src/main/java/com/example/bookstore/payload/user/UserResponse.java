package com.example.bookstore.payload.user;

import com.example.bookstore.model.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UUID id;

    private String email;

//    @JsonIgnore
//    private String password;

    private String name;

    private String roleName;

    private String phoneNumber;

    private String sex;

    @Temporal(TemporalType.DATE) //YYYY-MM-DD
    private Date birthday;

    private boolean isBlock;
}
