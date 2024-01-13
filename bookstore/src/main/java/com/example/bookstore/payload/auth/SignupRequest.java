package com.example.bookstore.payload.auth;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    private String sex;

    @Temporal(TemporalType.DATE) //YYYY-MM-DD
    private Date birthday;

}
