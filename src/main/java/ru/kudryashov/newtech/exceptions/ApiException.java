package ru.kudryashov.newtech.exceptions;

import lombok.Getter;

public class ApiException extends RuntimeException {

    @Getter
    protected String errorCode;

    public ApiException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
