package com.narwhal.basics.external.twilio.exception;


import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;

/**
 * @author Tomas de Priede
 */
public class InvalidPhoneNumberException extends BadRequestException {

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
