package com.example.simple_rest_app.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO (UUID id, String name, BigDecimal price) {
}
