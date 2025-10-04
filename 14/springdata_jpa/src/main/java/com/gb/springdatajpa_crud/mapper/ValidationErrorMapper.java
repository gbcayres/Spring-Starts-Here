package com.gb.springdatajpa_crud.mapper;

import com.gb.springdatajpa_crud.dto.ValidationError;
import jakarta.validation.ConstraintViolation;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Set;

@Component
public class ValidationErrorMapper {
    public List<ValidationError> mapFromBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(this::mapFieldError)
                .toList();
    }

    public List<ValidationError> mapFromConstraintViolations(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(this::mapConstraintViolation)
                .toList();
    }

    public ValidationError mapConstraintViolation(ConstraintViolation<?> violation) {
        return new ValidationError(
                violation.getPropertyPath().toString(),
                violation.getMessage(),
                violation.getInvalidValue()
        );
    }

    private ValidationError mapFieldError(FieldError fieldError) {
        return new ValidationError(
                fieldError.getField(),
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue()
        );
    }

}
