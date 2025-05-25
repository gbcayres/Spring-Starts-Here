package com.example.spring_starts_here_101.controller;

import com.example.spring_starts_here_101.exceptions.NotEnoughMoneyException;
import com.example.spring_starts_here_101.models.ErrorDetails;
import com.example.spring_starts_here_101.models.PaymentDetails;
import com.example.spring_starts_here_101.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class PaymentController {
    private static final Logger logger = Logger.getLogger(PaymentController.class.getName());

    @PostMapping("/payment")
    public ResponseEntity<?> makePayment(@RequestBody PaymentDetails paymentDetails) {
        logger.info("Received value: " + paymentDetails.getAmount());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(paymentDetails);
    }
}
