package com.narwhal.basics.external.sendgrid.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;

/**
 * @author Tomas de Priede
 */
public class InvalidEmailTemplateException extends BadRequestException {

    public InvalidEmailTemplateException(String message, Throwable t) {
        super(message, t);
    }
}
