package com.narwhal.basics.external.twilio.exception;


import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;

/**
 * @author Tomas de Priede
 */
public class InvalidSMSTemplateException extends BadRequestException {

    public InvalidSMSTemplateException(String message, Throwable t) {
        super(message, t);
    }
}
