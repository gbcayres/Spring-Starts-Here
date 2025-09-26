package com.example.springdatajpa_crud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        @NotBlank
        @Size(max = 255)
        String name,

        @NotBlank
        @Email
        String email
) {}
