package com.example.bookstore.payload.user;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    private String sex;

    @Temporal(TemporalType.DATE) //YYYY-MM-DD
    private Date birthday;

    private boolean isBlock;

}
