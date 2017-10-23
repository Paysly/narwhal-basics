package com.narwhal.basics.external.sendgrid.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Tomas de Priede
 */
public enum MailContentTypes {
    PLAIN("text/plain"), HTML("text/html");

    private String contentType;

    MailContentTypes(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    @JsonCreator
    public static MailContentTypes forValue(String value) {
        for (MailContentTypes type : MailContentTypes.values()) {
            if (type.getContentType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    @JsonValue
    public String toValue() {
        return this.getContentType();
    }
}
