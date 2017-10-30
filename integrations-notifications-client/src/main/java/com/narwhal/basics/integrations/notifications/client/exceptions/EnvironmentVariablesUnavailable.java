package com.narwhal.basics.integrations.notifications.client.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.ServiceUnavailableException;

public class EnvironmentVariablesUnavailable extends ServiceUnavailableException {

    public EnvironmentVariablesUnavailable(String message, Throwable throwable) {
        super(message, throwable);
    }
}
