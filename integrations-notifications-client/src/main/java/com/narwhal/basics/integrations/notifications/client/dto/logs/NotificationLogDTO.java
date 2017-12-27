package com.narwhal.basics.integrations.notifications.client.dto.logs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLogDTO implements Serializable {

    private String id;

    private String versionId;
    private String userFrom;
    private String userTo;
    private String notificationKey;
    private boolean email;
    private boolean sms;
    private boolean push;
    private Date createdAt;

}


