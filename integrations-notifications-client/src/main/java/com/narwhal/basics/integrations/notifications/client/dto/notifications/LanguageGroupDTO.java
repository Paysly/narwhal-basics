package com.narwhal.basics.integrations.notifications.client.dto.notifications;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageGroupDTO implements Serializable, Comparable<LanguageGroupDTO> {

    private String id;
    private String languageKey;
    private int sort;
    private int itemsTotal;
    private Date createdAt;
    private Date updatedAt;
    private String version;

    @Override
    public int compareTo(LanguageGroupDTO o) {
        return new Integer(this.sort).compareTo(o.sort);
    }
}
