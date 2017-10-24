package com.narwhal.basics.integrations.notifications.client.dto.notifications;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationItemDTO implements Serializable, Comparable<NotificationItemDTO> {
    private String id;
    private String notificationKey;
    private String groupNotificationKey;
    private int sort;
    private String version;
    private Date createdAt;
    private Date updatedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
    }

    public String getGroupNotificationKey() {
        return groupNotificationKey;
    }

    public void setGroupNotificationKey(String groupNotificationKey) {
        this.groupNotificationKey = groupNotificationKey;
        Pattern patternAlphaNumericCheck = Pattern.compile("^[a-z\\-]*$");
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int compareTo(NotificationItemDTO o) {
        return new Integer(this.sort).compareTo(o.sort);
    }
}
