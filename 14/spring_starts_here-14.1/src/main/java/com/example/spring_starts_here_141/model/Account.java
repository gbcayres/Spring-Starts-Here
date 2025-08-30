package com.example.spring_starts_here_141.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class Account {
    @Id
    private Long id;

    private String name;
    private BigDecimal amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
