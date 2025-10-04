package com.gb.springdatajpa_crud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        @NotNull
        @NotBlank
        @Size(max = 255)
        String name,

        @NotNull
        @NotBlank
        @Email
        String email
) {}
