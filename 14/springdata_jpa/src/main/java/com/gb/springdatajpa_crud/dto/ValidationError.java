package com.gb.springdatajpa_crud.dto;

public record ValidationError(
        String field,
        String message,
        Object rejectedValue
) {}
