package com.example.bookstore.exception;
import org.springframework.security.core.AuthenticationException;
public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(String message) {
        super(message);
    }
}