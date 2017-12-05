package com.narwhal.basics.integrations.notifications.client.exceptions;

import com.narwhal.basics.core.rest.exceptions.api.ServiceUnavailableException;

public class NotificationLogsSettingsUnavailable extends ServiceUnavailableException {
    public NotificationLogsSettingsUnavailable(String message, Throwable throwable) {
        super(message, throwable);
    }
}