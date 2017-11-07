package com.narwhal.basics.external.twilio.dto;

import com.narwhal.basics.core.rest.utils.ToStringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;

public class SmsResponse implements Serializable {
    private boolean success;
    private String messageSent;

    public SmsResponse() {
    }

    public void init(String messageSent) {
        this.success = true;
        this.messageSent = messageSent;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessageSent() {
        return messageSent;
    }

    public void setMessageSent(String messageSent) {
        this.messageSent = messageSent;
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
