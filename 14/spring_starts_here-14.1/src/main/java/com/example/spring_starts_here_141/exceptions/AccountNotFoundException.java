package com.example.spring_starts_here_141.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("Could not find account " + id);
    }
}
