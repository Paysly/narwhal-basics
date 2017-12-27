package com.narwhal.basics.external.sendgrid.dto.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.core.rest.utils.ToStringUtils;
import com.narwhal.basics.external.sendgrid.types.MailContentTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Tomas de Priede
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailContent implements Serializable {

    private MailContentTypes type;
    private String value;

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
