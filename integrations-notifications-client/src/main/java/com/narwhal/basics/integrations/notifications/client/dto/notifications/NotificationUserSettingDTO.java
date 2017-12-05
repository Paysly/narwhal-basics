package com.narwhal.basics.integrations.notifications.client.dto.notifications;

import com.googlecode.objectify.annotation.Id;
import com.narwhal.basics.core.rest.utils.ToStringUtils;
import com.narwhal.basics.integrations.notifications.client.exceptions.NoUserSettingSelectedException;
import com.narwhal.basics.integrations.notifications.client.types.NotificationMechanismType;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class NotificationUserSettingDTO implements Serializable {

    @Id
    private String id; // userid{}groupKey
    //
    private String userId;
    //
    private String groupKey;
    //
    private Set<NotificationMechanismType> selectedValues = new TreeSet<>();
    //
    private Date createdAt;
    //
    private Date updatedAt;

    public void init(String userId, String groupKey, Set<NotificationMechanismType> selectedValues) {
        this.userId = userId;
        this.groupKey = StringUtils.lowerCase(groupKey);
        //
        this.id = DigestUtils.sha256Hex(userId + "{}" + groupKey);
        //
        this.selectedValues = selectedValues;
        this.selectedValues.remove(NotificationMechanismType.NONE);
        //
        if (this.selectedValues.isEmpty()) {
            throw new NoUserSettingSelectedException(userId);
        }
        //
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public Set<NotificationMechanismType> getSelectedValues() {
        return selectedValues;
    }

    public void setSelectedValues(Set<NotificationMechanismType> selectedValues) {
        this.selectedValues = selectedValues;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public String toString() {
        return ToStringUtils.toString(this);
    }
}
