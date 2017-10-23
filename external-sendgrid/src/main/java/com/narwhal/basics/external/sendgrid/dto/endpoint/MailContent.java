package com.narwhal.basics.external.sendgrid.dto.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.core.rest.utils.ToStringUtils;
import com.narwhal.basics.external.sendgrid.types.MailContentTypes;

import java.io.Serializable;

/**
 * @author Tomas de Priede
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailContent implements Serializable {

    private MailContentTypes type;
    private String value;

    public MailContent() {
    }

    public MailContent(MailContentTypes type, String value) {
        this.type = type;
        this.value = value;
    }

    public MailContentTypes getType() {
        return type;
    }

    public void setType(MailContentTypes type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
