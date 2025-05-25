package com.example.spring_starts_here_101.services;

import com.example.spring_starts_here_101.exceptions.NotEnoughMoneyException;
import com.example.spring_starts_here_101.models.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentDetails processPayment() {
        throw new NotEnoughMoneyException();
    }
}
