package com.narwhal.basics.external.core.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.ServiceUnavailableException;

public class SendgridProviderInfoNotConfiguredException extends ServiceUnavailableException {

    public static final String MESSAGE = "Sendgrid provider info not configured yet. Missing info: ";

    public SendgridProviderInfoNotConfiguredException(String field) {
        super(MESSAGE + field);
    }
}
