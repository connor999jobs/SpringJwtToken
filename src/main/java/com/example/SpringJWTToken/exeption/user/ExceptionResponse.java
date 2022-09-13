package com.example.SpringJWTToken.exeption.user;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class ExceptionResponse {
    private String message;
    private LocalDateTime dateTime;

}
