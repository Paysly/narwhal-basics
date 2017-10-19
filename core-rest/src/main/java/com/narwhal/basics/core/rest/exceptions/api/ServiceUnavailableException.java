package com.narwhal.basics.core.rest.exceptions.api;

/**
 * @author Tomas de Priede
 */
public class ServiceUnavailableException extends ApiException {

    public ServiceUnavailableException(String message) {
        super(503, message);
    }

    public ServiceUnavailableException(String s, Throwable e) {
        super(503, s, e);
    }
}
