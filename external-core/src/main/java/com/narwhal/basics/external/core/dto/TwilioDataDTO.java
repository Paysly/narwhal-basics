package com.narwhal.basics.external.core.dto;

import com.narwhal.basics.core.rest.utils.ToStringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;

public class TwilioDataDTO implements Serializable {
    private String fromNumber;
    private String sid;
    private String token;
    private String url;

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
