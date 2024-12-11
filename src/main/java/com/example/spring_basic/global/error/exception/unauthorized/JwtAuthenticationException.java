package com.example.spring_basic.global.error.exception.unauthorized;

import io.jsonwebtoken.JwtException;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException() {
        super("Jwt Authentication Exception");
    }

    public JwtAuthenticationException(String message, JwtException cause) {
        super(message, cause);
    }
}
