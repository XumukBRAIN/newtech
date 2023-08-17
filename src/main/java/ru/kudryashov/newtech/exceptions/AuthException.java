package ru.kudryashov.newtech.exceptions;

public class AuthException extends ApiException {
    public AuthException(String message, String errorCode) {
        super(errorCode, message);
    }
}
