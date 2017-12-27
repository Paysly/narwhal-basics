package com.narwhal.basics.integrations.notifications.client.dto.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessageDataResponseDTO implements Serializable {
    private boolean notified = false;
    private int status;
    private String errorMessage;

    public void setError(int code, String message) {
        this.notified = false;
        this.setStatus(code);
        this.setErrorMessage(message);
    }
}
