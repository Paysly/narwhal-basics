package com.narwhal.basics.integrations.notifications.client.dto.notifications;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public int compareTo(NotificationItemDTO o) {
        return new Integer(this.sort).compareTo(o.sort);
    }
}
