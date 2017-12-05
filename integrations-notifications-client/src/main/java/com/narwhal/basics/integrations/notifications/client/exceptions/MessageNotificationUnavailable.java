package com.narwhal.basics.integrations.notifications.client.exceptions;

import com.narwhal.basics.core.rest.exceptions.api.ServiceUnavailableException;

public class MessageNotificationUnavailable extends ServiceUnavailableException {
    public MessageNotificationUnavailable(String message, Throwable throwable) {
        super(message, throwable);
    }
}