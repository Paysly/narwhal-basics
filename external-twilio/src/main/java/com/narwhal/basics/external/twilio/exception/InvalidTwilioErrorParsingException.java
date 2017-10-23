package com.narwhal.basics.external.twilio.exception;


import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;

/**
 * @author Tomas de Priede
 */
public class InvalidTwilioErrorParsingException extends BadRequestException {

    public InvalidTwilioErrorParsingException(String message, Throwable e) {
        super(message, e);
    }
}
