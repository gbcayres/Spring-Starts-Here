package com.gb.springdatajpa_crud.exception;

import com.gb.springdatajpa_crud.enums.ErrorCode;

import java.util.UUID;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String resourceName, UUID resourceId) {
        super(ErrorCode.NOT_FOUND, resourceName + " with id " + resourceId + " not found");
    }
}
