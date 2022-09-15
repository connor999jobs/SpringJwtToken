package com.example.SpringJWTToken.exeption.jwt;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;


@ControllerAdvice
public class JwtExceptionsHandler {

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<Object> handleUserServiceException() {
        JwtExceptionResponse exceptionMessage = new JwtExceptionResponse();
        exceptionMessage.setTimestamp(LocalDateTime.now());
        exceptionMessage.setMessage("The token has expired. Please re-login");
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}

