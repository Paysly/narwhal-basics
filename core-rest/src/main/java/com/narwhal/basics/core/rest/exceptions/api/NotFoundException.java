package com.narwhal.basics.core.rest.exceptions.api;

/**
 * @author Tomas de Priede
 */
public class NotFoundException extends ApiException {

    public NotFoundException(String message) {
        super(404, message);
    }

    public NotFoundException(String message, Throwable t) {
        super(404, message, t);
    }
}
