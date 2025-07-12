package com.example.simple_rest_app.dto;

import com.example.simple_rest_app.validation.CreateGroup;
import com.example.simple_rest_app.validation.UpdateGroup;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank(message = "The product name field is required.", groups = {CreateGroup.class, UpdateGroup.class})
        @Size(min = 2, max = 100, message = "The product name field must have between 3 and 100 characters.", groups = {CreateGroup.class, UpdateGroup.class})
        String name,

        @NotNull(message = "The product price field is required.", groups = {CreateGroup.class})
        @DecimalMin(value = "0.01", message = "The product price field must be bigger than 0.", groups = {CreateGroup.class})
        @Digits(integer = 10, fraction = 2, message = "The price should be accurate to two decimal places.", groups = {CreateGroup.class, UpdateGroup.class})
        BigDecimal price
) {}