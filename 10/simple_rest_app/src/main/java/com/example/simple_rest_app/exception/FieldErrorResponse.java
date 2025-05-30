package com.example.simple_rest_app.exception;

public record FieldErrorResponse(String field, String message, Object rejectedValue) {}
