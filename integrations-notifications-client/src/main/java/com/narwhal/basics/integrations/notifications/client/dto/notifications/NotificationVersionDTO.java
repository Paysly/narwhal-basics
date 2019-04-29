package com.narwhal.basics.integrations.notifications.client.dto.notifications;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.integrations.notifications.client.types.NotificationVersionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationVersionDTO implements Serializable {
    private String id;
    private String description;
    private String versionIdToCopy;
    private Date createdAt;
    private Date updatedAt;
    private NotificationVersionType versionType;
}
