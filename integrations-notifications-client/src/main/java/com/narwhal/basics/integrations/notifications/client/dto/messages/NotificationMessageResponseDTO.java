package com.narwhal.basics.integrations.notifications.client.dto.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessageResponseDTO implements Serializable {
    private boolean notified = false;
    private NotificationMessageDataResponseDTO emailResponse;
    private NotificationMessageDataResponseDTO smsResponse;
    private NotificationMessageDataResponseDTO pushResponse;

}