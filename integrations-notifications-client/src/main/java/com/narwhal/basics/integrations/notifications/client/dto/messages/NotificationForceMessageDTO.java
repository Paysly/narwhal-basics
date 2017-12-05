package com.narwhal.basics.integrations.notifications.client.dto.messages;

import com.narwhal.basics.integrations.notifications.client.dto.messages.NotificationMessageDTO;
import com.narwhal.basics.integrations.notifications.client.types.NotificationMechanismType;

public class NotificationForceMessageDTO extends NotificationMessageDTO {
    private NotificationMechanismType forcedMechanismType;

    public NotificationMechanismType getForcedMechanismType() {
        return forcedMechanismType;
    }

    public void setForcedMechanismType(NotificationMechanismType forcedMechanismType) {
        this.forcedMechanismType = forcedMechanismType;
    }
}