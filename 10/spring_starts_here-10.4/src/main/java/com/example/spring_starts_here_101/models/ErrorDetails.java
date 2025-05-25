package com.example.spring_starts_here_101.models;

public class ErrorDetails {
    private final String message;

    public ErrorDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
