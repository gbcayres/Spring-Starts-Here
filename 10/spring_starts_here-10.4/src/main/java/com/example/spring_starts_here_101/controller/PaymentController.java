package com.example.spring_starts_here_101.controller;

import com.example.spring_starts_here_101.exceptions.NotEnoughMoneyException;
import com.example.spring_starts_here_101.models.ErrorDetails;
import com.example.spring_starts_here_101.models.PaymentDetails;
import com.example.spring_starts_here_101.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<?> makePayment() {
        try {
            PaymentDetails paymentDetails = paymentService.processPayment();

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(paymentDetails);
        } catch(NotEnoughMoneyException e) {
            ErrorDetails errorDetails = new ErrorDetails(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorDetails);
        }
    }
}
