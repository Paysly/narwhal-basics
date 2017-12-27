package com.narwhal.basics.external.firebase.dto;

import com.narwhal.basics.core.rest.utils.ToStringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushResponse implements Serializable {
    private boolean success;
    private String titleSent;
    private String bodySent;

    public void init(String titleSent, String bodySent) {
        this.success = true;
        this.titleSent = titleSent;
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
