package com.example.simple_rest_app.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank(message = "The product name field is required.")
        @Size(min = 2, max = 100, message = "The product name field must have between 3 and 100 characters.")
        String name,

        @NotNull(message = "The product price field is required.")
        @DecimalMin(value = "0.01", message = "The product price field must be bigger than 0.")
        @Digits(integer = 10, fraction = 2, message = "The price should be accurate to two decimal places.")
        BigDecimal price
) {}