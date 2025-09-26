package com.example.springdatajpa_crud.dto;

import java.util.UUID;

public record CustomerResponse(UUID id, String name, String email) {}
