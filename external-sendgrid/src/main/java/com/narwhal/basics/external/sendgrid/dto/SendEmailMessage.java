package com.narwhal.basics.external.sendgrid.dto;

import com.narwhal.basics.core.rest.utils.ToStringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Tomas de Priede
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailMessage implements Serializable {

    private String to;
    private String subject;
    private Map<String, Object> model;
    private String htmlTemplate;
    private String plainTemplate;

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
