package com.example.spring_starts_here_113.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(UUID id, String name, BigDecimal price) {}
