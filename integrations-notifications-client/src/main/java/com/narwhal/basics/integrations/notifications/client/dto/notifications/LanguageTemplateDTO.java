package com.narwhal.basics.integrations.notifications.client.dto.notifications;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageTemplateDTO implements Serializable {

    private String id;
    //
    private String templateName; // This will be primary used as language code
    //
    private String versionId;
    //
    private String languageKey;
    //
    private String text;
}
