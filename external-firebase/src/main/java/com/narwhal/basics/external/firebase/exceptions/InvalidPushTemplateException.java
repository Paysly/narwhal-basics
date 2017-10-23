package com.narwhal.basics.external.firebase.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;

/**
 * @author Tomas de Priede
 */
public class InvalidPushTemplateException extends BadRequestException {

    public InvalidPushTemplateException(String message, Throwable t) {
        super(message, t);
    }
}
