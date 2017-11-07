package com.narwhal.basics.external.twilio.dto;

import com.narwhal.basics.core.rest.utils.ToStringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Tomas de Priede
 */
public class SendPhoneMessage implements Serializable {

    private String to;
    private String subject;
    private Map<String, Object> model;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
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
