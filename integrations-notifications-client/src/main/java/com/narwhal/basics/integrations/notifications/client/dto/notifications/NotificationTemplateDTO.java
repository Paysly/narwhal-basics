package com.narwhal.basics.integrations.notifications.client.dto.notifications;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.integrations.notifications.client.types.NotificationMechanismType;
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
public class NotificationTemplateDTO implements Serializable {

    private String id;
    //
    private String templateName; // This will be primary used as language code
    //
    private String versionId;
    //
    private String notificationKey;
    //
    private NotificationMechanismType mechanismType;
    //
    private String title;
    //
    private String bodyHtml;
    //
    private String bodyPlain;
    //
    private String pushActionLink;
    //
    private String headerTemplateId;
    //
    private String footerTemplateId;
    //
    private boolean requireVerifiedSource;
    //
    private Date createdAt;
    //
    private Date updatedAt;

}
