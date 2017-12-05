package com.narwhal.basics.integrations.notifications.client.exceptions;

import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;

public class NoUserSettingSelectedException extends BadRequestException {

    public NoUserSettingSelectedException(String userId) {
        super("You should add at least one user setting for: " + userId);
    }
}
