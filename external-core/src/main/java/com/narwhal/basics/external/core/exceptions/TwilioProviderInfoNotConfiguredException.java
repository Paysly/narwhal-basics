package com.narwhal.basics.external.core.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.ServiceUnavailableException;

public class TwilioProviderInfoNotConfiguredException extends ServiceUnavailableException {

    public static final String MESSAGE = "Twilio provider info not configured yet. Missing info: ";

    public TwilioProviderInfoNotConfiguredException(String field) {
        super(MESSAGE + field);
    }
}
