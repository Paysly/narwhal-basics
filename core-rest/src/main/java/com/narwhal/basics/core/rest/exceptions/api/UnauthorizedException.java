package com.narwhal.basics.core.rest.exceptions.api;

/**
 * @author Tomas de Priede
 */
public class UnauthorizedException extends ApiException {

    public UnauthorizedException(String message) {
        super(401, message);
    }

    public UnauthorizedException(String message, Throwable throwable) {
        super(401, message, throwable);
    }
}
