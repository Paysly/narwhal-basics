package com.narwhal.basics.integrations.notifications.client.dto.notifications;


import java.io.Serializable;
import java.util.List;

public class NotificationVersionsDTO implements Serializable {

    private List<NotificationVersionDTO> versions;

    public NotificationVersionsDTO() {
    }

    public NotificationVersionsDTO(List<NotificationVersionDTO> versions) {
        this.versions = versions;
    }

    public List<NotificationVersionDTO> getVersions() {
        return versions;
    }

    public void setVersions(List<NotificationVersionDTO> versions) {
        this.versions = versions;
    }
}
