package com.narwhal.basics.integrations.notifications.client.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.ServiceUnavailableException;

public class ApplicationSettingsUnavailable extends ServiceUnavailableException {

    public ApplicationSettingsUnavailable(String message, Throwable throwable) {
        super(message, throwable);
    }
}
