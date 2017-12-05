package com.narwhal.basics.integrations.notifications.client.exceptions;

import com.narwhal.basics.core.rest.exceptions.api.ServiceUnavailableException;

public class UserNotificationUnavailable extends ServiceUnavailableException {
    public UserNotificationUnavailable(String message, Throwable throwable) {
        super(message, throwable);
    }
}