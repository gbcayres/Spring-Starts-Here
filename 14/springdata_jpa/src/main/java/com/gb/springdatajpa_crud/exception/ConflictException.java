package com.gb.springdatajpa_crud.exception;

import com.gb.springdatajpa_crud.enums.ErrorCode;

public class ConflictException extends BusinessException {
    public ConflictException(String message) {
        super(ErrorCode.CONFLICT, message);
    }
}
