package com.narwhal.basics.integrations.notifications.client.dto.messages;

import com.narwhal.basics.core.rest.utils.ToStringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessageDTO implements Serializable {
    private String version;
    private String userTo;
    private String notificationKey;
    private String templateName;
    private Serializable model;

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
