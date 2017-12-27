package com.narwhal.basics.external.sendgrid.dto;

import com.narwhal.basics.core.rest.utils.ToStringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailResponse implements Serializable {
    private boolean success;
    private String subjectSent;
    private String bodyHtmlSent;
    private String bodyPlainSent;


    public void init(String subjectSent, String bodyHtmlSent, String bodyPlainSent) {
        this.success = true;
        this.subjectSent = subjectSent;
        this.bodyHtmlSent = bodyHtmlSent;
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
