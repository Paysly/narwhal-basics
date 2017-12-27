package com.narwhal.basics.integrations.notifications.client.dto.messages;

import com.narwhal.basics.integrations.notifications.client.dto.messages.NotificationMessageDTO;
import com.narwhal.basics.integrations.notifications.client.types.NotificationMechanismType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationForceMessageDTO extends NotificationMessageDTO {
    private NotificationMechanismType forcedMechanismType;
}