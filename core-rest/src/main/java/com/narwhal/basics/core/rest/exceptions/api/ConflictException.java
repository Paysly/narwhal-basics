package com.narwhal.basics.core.rest.exceptions.api;

/**
 * @author Tomas de Priede
 */
public class ConflictException extends ApiException {

    public ConflictException(String message) {
        super(409, message);
    }
}
