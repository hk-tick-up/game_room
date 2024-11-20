package com.example.gameroom.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandleController {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandler(IllegalArgumentException e){
        e.printStackTrace();
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
