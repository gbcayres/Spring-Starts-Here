package com.example.spring_starts_here_101.exceptions;

import com.example.spring_starts_here_101.models.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleNotEnoughMoneyException(NotEnoughMoneyException e) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }
}
