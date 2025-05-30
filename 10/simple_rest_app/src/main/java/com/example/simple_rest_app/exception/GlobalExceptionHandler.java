package com.example.simple_rest_app.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<FieldErrorResponse> errors = extractFieldErrors(ex);

        ProblemDetail problemDetail =  createBaseProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Validation error",
                "One or more fields are invalid. Check 'fieldErrors' and try again.",
                URI.create(request.getRequestURI())
        );
        problemDetail.setProperty("fieldErrors", errors);

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleBindingException(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        return createBaseProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Invalid param error.",
                String.format("The '%s' param received the invalid value '%s'.", ex.getName(), ex.getValue()),
                URI.create(request.getRequestURI())
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFound(
            ProductNotFoundException ex, HttpServletRequest request) {
        return createBaseProblemDetail(
                HttpStatus.NOT_FOUND,
                "Product not found.",
                ex.getMessage(),
                URI.create(request.getRequestURI())
        );
    }

    private ProblemDetail createBaseProblemDetail(
            HttpStatus status, String title, String detail, URI instance) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setInstance(instance);
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    private List<FieldErrorResponse> extractFieldErrors(MethodArgumentNotValidException ex) {
        return  ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorResponse(
                        error.getField(),
                        error.getDefaultMessage(),
                        error.getRejectedValue()
                ))
                .collect(Collectors.toList());
    }
}
