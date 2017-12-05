package com.narwhal.basics.integrations.notifications.client.dto.messages;

import java.io.Serializable;

public class NotificationMessageResponseDTO implements Serializable {
    private boolean notified = false;
    private NotificationMessageDataResponseDTO emailResponse;
    private NotificationMessageDataResponseDTO smsResponse;
    private NotificationMessageDataResponseDTO pushResponse;


    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public NotificationMessageDataResponseDTO getEmailResponse() {
        return emailResponse;
    }

    public void setEmailResponse(NotificationMessageDataResponseDTO emailResponse) {
        this.emailResponse = emailResponse;
    }

    public NotificationMessageDataResponseDTO getSmsResponse() {
        return smsResponse;
    }

    public void setSmsResponse(NotificationMessageDataResponseDTO smsResponse) {
        this.smsResponse = smsResponse;
    }

    public NotificationMessageDataResponseDTO getPushResponse() {
        return pushResponse;
    }

    public void setPushResponse(NotificationMessageDataResponseDTO pushResponse) {
        this.pushResponse = pushResponse;
    }
}