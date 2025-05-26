package com.example.simple_rest_app.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, BigDecimal price) {
}
