package com.narwhal.basics.integrations.notifications.client.dto.settings;

import java.io.Serializable;

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
public class FirebaseSettingsDTO implements Serializable {

    private String serverKey;
    private String messagingUrl;
    private String appUrl;
    private String iconUrl;
    private String iosKey;
    private String androidKey;
}
