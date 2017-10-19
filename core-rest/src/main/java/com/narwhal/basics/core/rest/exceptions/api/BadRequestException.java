package com.narwhal.basics.core.rest.exceptions.api;

/**
 * @author Tomas de Priede
 */
public class BadRequestException extends ApiException {

    public BadRequestException(String message) {
        super(400, message);
    }

    public BadRequestException(String s, Throwable e) {
        super(400, s, e);
    }
}
