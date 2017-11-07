package com.narwhal.basics.external.firebase.dto;

import com.narwhal.basics.core.rest.utils.ToStringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;

public class PushResponse implements Serializable {
    private boolean success;
    private String titleSent;
    private String bodySent;

    public PushResponse() {
    }

    public void init(String titleSent, String bodySent) {
        this.success = true;
        this.titleSent = titleSent;
        this.bodySent = bodySent;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTitleSent() {
        return titleSent;
    }

    public void setTitleSent(String titleSent) {
        this.titleSent = titleSent;
    }

    public String getBodySent() {
        return bodySent;
    }

    public void setBodySent(String bodySent) {
        this.bodySent = bodySent;
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
