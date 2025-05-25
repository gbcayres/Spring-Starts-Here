package com.example.spring_starts_here_101.models;

public class PaymentDetails {
    private final double amount;

    public PaymentDetails(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
