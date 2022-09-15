package com.example.SpringJWTToken.exeption.jwt;


import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.LocalDateTime;


@ControllerAdvice
public class JwtExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = JwtAuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public JwtExceptionResponse handleUserServiceException(JwtAuthenticationException exception) {
        return new JwtExceptionResponse(HttpStatus.FORBIDDEN.value(), LocalDateTime.now(),exception.getMessage());
    }
}

