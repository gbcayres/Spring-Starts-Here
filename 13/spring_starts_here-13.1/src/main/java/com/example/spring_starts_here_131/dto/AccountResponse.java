package com.example.spring_starts_here_131.dto;

import com.example.spring_starts_here_131.model.Account;

import java.math.BigDecimal;

public record AccountResponse(Long id, String name, BigDecimal amount) {
    public static AccountResponse fromAccount(Account account) {
        return new AccountResponse(account.getId(), account.getName(), account.getAmount());
    }
}
