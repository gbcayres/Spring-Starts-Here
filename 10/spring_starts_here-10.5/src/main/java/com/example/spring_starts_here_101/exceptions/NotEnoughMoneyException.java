package com.example.spring_starts_here_101.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException () {
        super("Not enough money to conclude the operation.");
    }
}
