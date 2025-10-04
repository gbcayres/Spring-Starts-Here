package com.gb.springdatajpa_crud.exception;

import com.gb.springdatajpa_crud.dto.ValidationError;
import com.gb.springdatajpa_crud.enums.ErrorCode;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.Instant;
import java.util.List;

public class ProblemDetailBuilder {

    private final ProblemDetail problemDetail;

    private ProblemDetailBuilder(ErrorCode errorCode) {
        this.problemDetail = ProblemDetail.forStatus(errorCode.status());
        this.problemDetail.setTitle(errorCode.title());
        this.problemDetail.setProperty("code", errorCode.code());
        this.problemDetail.setProperty("timestamp", Instant.now());
    }

    public static ProblemDetailBuilder createProblemDetailfromErrorCode(ErrorCode errorCode) {
        return new ProblemDetailBuilder(errorCode);
    }

    public ProblemDetailBuilder withDetail(String detail) {
        problemDetail.setDetail(detail);
        return this;
    }

    public ProblemDetailBuilder withInstance(String instanceURI) {
        URI instance = URI.create(instanceURI);
        problemDetail.setInstance(instance);
        return this;
    }

    public ProblemDetailBuilder withErrors(List<ValidationError> errors) {
        problemDetail.setProperty("errors", errors);
        return this;
    }

    public ProblemDetail build() { return problemDetail; }
}
