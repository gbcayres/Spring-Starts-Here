package com.gb.springdatajpa_crud.exception;

import com.gb.springdatajpa_crud.enums.ErrorCode;

public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode, String message ) {
      super(message);
      this.errorCode = errorCode;
    }

  public ErrorCode getErrorCode() { return errorCode; }
}
