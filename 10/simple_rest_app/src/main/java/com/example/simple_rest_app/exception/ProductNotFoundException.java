package com.example.simple_rest_app.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID id) {
        super("Product with id " + id.toString() + " not found. ");
    }
}
