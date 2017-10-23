package com.narwhal.basics.external.twilio.exception;


import com.narwhal.basics.core.rest.exceptions.api.ServiceUnavailableException;

/**
 * @author Tomas de Priede
 */
public class SMSServiceNotAvailableException extends ServiceUnavailableException {

    public SMSServiceNotAvailableException(String message) {
        super(message);
    }
}
