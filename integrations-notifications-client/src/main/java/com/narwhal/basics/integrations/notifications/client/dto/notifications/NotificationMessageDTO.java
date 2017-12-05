package com.narwhal.basics.integrations.notifications.client.dto.notifications;

import com.narwhal.basics.core.rest.utils.ToStringUtils;

import java.io.Serializable;
import java.util.Map;

public class NotificationMessageDTO implements Serializable {
    private String version;
    private String userTo;
    private String notificationKey;
    private String templateName;
    private Map<String, Object> model;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
