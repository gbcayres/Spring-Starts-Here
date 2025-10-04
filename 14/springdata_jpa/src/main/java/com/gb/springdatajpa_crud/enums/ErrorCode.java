package com.gb.springdatajpa_crud.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    CONFLICT(HttpStatus.CONFLICT, "CONFLICT", "Resource already exists"),
    VALIDATION(HttpStatus.BAD_REQUEST, "VALIDATION", "Validation failed"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND", "Resource not found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "Unexpected error");

    private final HttpStatus status;
    private final String code;
    private final String title;

    ErrorCode(HttpStatus status, String code, String title) {
        this.status = status;
        this.code = code;
        this.title = title;
    }

    public HttpStatus status() { return status; }
    public String code() { return code; }
    public String title() { return title; }
}
