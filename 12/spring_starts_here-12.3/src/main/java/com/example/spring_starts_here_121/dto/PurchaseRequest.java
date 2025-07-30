package com.example.spring_starts_here_121.dto;

import java.math.BigDecimal;

public record PurchaseRequest(int id, String product, BigDecimal price) {}
