package com.example.SpringJWTToken.exeption.jwt;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
public class JwtExceptionResponse {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
}
