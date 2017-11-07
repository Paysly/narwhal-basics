package com.narwhal.basics.external.sendgrid.dto;

import com.narwhal.basics.core.rest.utils.ToStringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;

public class MailResponse implements Serializable {
    private boolean success;
    private String subjectSent;
    private String bodyHtmlSent;
    private String bodyPlainSent;

    public MailResponse() {
    }

    public void init(String subjectSent, String bodyHtmlSent, String bodyPlainSent) {
        this.success = true;
        this.subjectSent = subjectSent;
        this.bodyHtmlSent = bodyHtmlSent;
        this.bodyPlainSent = bodyPlainSent;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSubjectSent() {
        return subjectSent;
    }

    public void setSubjectSent(String subjectSent) {
        this.subjectSent = subjectSent;
    }

    public String getBodyHtmlSent() {
        return bodyHtmlSent;
    }

    public void setBodyHtmlSent(String bodyHtmlSent) {
        this.bodyHtmlSent = bodyHtmlSent;
    }

    public String getBodyPlainSent() {
        return bodyPlainSent;
    }

    public void setBodyPlainSent(String bodyPlainSent) {
        this.bodyPlainSent = bodyPlainSent;
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
