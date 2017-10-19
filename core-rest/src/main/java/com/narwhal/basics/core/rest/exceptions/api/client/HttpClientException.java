package com.narwhal.basics.core.rest.exceptions.api.client;


import com.narwhal.basics.core.rest.exceptions.api.ApiException;

import java.io.Serializable;

/**
 * @author Tomas de Priede
 */
public class HttpClientException extends ApiException {

    private Serializable object;

    public HttpClientException(int code, String message, Serializable object) {
        super(code, message);
        this.object = object;
    }

    public Serializable getObject() {
        return object;
    }

    public String getObjectAsString() {
        return (String) object;
    }
}
