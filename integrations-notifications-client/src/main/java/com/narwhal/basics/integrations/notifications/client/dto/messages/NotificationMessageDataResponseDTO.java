package com.narwhal.basics.integrations.notifications.client.dto.messages;

import java.io.Serializable;

public class NotificationMessageDataResponseDTO implements Serializable {
    private boolean notified = false;
    private int status;
    private String errorMessage;

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setError(int code, String message) {
        this.notified = false;
        this.setStatus(code);
        this.setErrorMessage(message);
    }
}
