package com.gb.springdatajpa_crud.exception;

import com.gb.springdatajpa_crud.dto.ValidationError;
import com.gb.springdatajpa_crud.enums.ErrorCode;
import com.gb.springdatajpa_crud.mapper.ValidationErrorMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import jakarta.validation.Validation;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ValidationErrorMapper validationErrorMapper;

    public GlobalExceptionHandler(ValidationErrorMapper validationErrorMapper) {
        this.validationErrorMapper = validationErrorMapper;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<ValidationError> errors = validationErrorMapper.mapFromBindingResult(ex.getBindingResult());

        var problemDetail = ProblemDetailBuilder.createProblemDetailfromErrorCode(ErrorCode.VALIDATION)
                .withDetail("Validation failed. Check 'errors[]' for more information.")
                .withInstance(request.getRequestURI())
                .withErrors(errors)
                .build();

        return responseFromProblemDetail(problemDetail);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolationException(
            ConstraintViolationException ex, HttpServletRequest request) {

        List<ValidationError> errors = validationErrorMapper.mapFromConstraintViolations(ex.getConstraintViolations());

        var problemDetail = ProblemDetailBuilder.createProblemDetailfromErrorCode(ErrorCode.VALIDATION)
                .withDetail(ex.getMessage())
                .withInstance(request.getRequestURI())
                .withErrors(errors)
                .build();

        return responseFromProblemDetail(problemDetail);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetail> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        var problemDetail = ProblemDetailBuilder.createProblemDetailfromErrorCode(ErrorCode.VALIDATION)
                .withDetail("Parameter " + ex.getName() + " should be of type " + ex.getRequiredType().getSimpleName())
                .withInstance(request.getRequestURI())
                .build();

        return responseFromProblemDetail(problemDetail);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ProblemDetail> handleConflict(
            ConflictException ex,
            HttpServletRequest request) {

        var problemDetail = ProblemDetailBuilder.createProblemDetailfromErrorCode(ex.getErrorCode())
                .withDetail(ex.getMessage())
                .withInstance(request.getRequestURI())
                .build();

        return responseFromProblemDetail(problemDetail);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetailBuilder.createProblemDetailfromErrorCode(ex.getErrorCode())
                .withDetail(ex.getMessage())
                .withInstance(request.getRequestURI())
                .build();

        return responseFromProblemDetail(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpectedException(Exception ex, HttpServletRequest request) {
        var problemDetail = ProblemDetailBuilder.createProblemDetailfromErrorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .withDetail("An unexpected error occurred.")
                .withInstance(request.getRequestURI())
                .build();

        return responseFromProblemDetail(problemDetail);
    }

    public ResponseEntity<ProblemDetail> responseFromProblemDetail(ProblemDetail problemDetail) {
        return ResponseEntity
                .status(problemDetail.getStatus())
                .body(problemDetail);
    }
}
