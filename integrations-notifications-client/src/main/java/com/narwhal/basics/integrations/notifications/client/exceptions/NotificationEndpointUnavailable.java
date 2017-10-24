package com.narwhal.basics.integrations.notifications.client.exceptions;


import com.narwhal.basics.core.rest.exceptions.api.ServiceUnavailableException;

public class NotificationEndpointUnavailable extends ServiceUnavailableException {

    public NotificationEndpointUnavailable(String message, Throwable throwable) {
        super(message, throwable);
    }
}
