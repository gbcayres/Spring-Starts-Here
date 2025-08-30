package com.example.spring_starts_here_141.dto;

import java.math.BigDecimal;

public record TransferRequest(Long senderId, Long receiverId, BigDecimal amount) {
}
