package com.narwhal.basics.integrations.notifications.client.dto.logs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLogDetailDTO implements Serializable {

    private String id;
    private String emailTitle;
    private String emailBodyHtml;
    private String smsText;
    private String pushTitle;
    private String pushBody;
    private Date createdAt;
    private String email;
    private String phone;
}

