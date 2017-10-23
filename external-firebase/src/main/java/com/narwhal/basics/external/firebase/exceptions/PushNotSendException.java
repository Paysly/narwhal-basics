package com.narwhal.basics.external.firebase.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;

/**
 * @author Tomas de Priede
 */
public class PushNotSendException extends BadRequestException {

    public PushNotSendException(String message, Throwable t) {
        super(message, t);
    }
}
