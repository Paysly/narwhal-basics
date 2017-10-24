package com.narwhal.basics.integrations.notifications.client.dto.notifications;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.integrations.notifications.client.types.NotificationMechanismType;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationGroupDTO implements Serializable, Comparable<NotificationGroupDTO> {
    private String id;
    private String notificationKey;
    private int sort;
    private int itemsTotal;
    private Date createdAt;
    private Date updatedAt;
    private String version;
    private Set<NotificationMechanismType> supportedMechanisms = new TreeSet<>();
    private Set<NotificationMechanismType> defaultMechanisms = new TreeSet<>();


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

    public Set<NotificationMechanismType> getSupportedMechanisms() {
        return supportedMechanisms;
    }

    public void setSupportedMechanisms(Set<NotificationMechanismType> supportedMechanisms) {
        this.supportedMechanisms = supportedMechanisms;
    }

    public Set<NotificationMechanismType> getDefaultMechanisms() {
        return defaultMechanisms;
    }

    public void setDefaultMechanisms(Set<NotificationMechanismType> defaultMechanisms) {
        this.defaultMechanisms = defaultMechanisms;
    }

    public int getItemsTotal() {
        return itemsTotal;
    }

    public void setItemsTotal(int itemsTotal) {
        this.itemsTotal = itemsTotal;
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
    public int compareTo(NotificationGroupDTO o) {
        return new Integer(this.sort).compareTo(o.sort);
    }
}
