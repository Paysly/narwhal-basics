package com.narwhal.basics.external.sendgrid.dto.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.narwhal.basics.core.rest.utils.ToStringUtils;

import java.io.Serializable;

/**
 * @author Tomas de Priede
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailAddress implements Serializable {

    private String email;

    public MailAddress() {
    }

    public MailAddress(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
