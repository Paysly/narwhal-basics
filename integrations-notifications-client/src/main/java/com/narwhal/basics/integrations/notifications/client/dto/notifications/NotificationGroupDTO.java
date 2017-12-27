package com.narwhal.basics.integrations.notifications.client.dto.notifications;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.integrations.notifications.client.types.NotificationMechanismType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public int compareTo(NotificationGroupDTO o) {
        return new Integer(this.sort).compareTo(o.sort);
    }
}
