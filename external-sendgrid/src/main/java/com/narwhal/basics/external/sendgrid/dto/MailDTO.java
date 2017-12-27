package com.narwhal.basics.external.sendgrid.dto;

import com.fasterxml.jackson.annotation.*;
import com.narwhal.basics.core.rest.utils.ToStringUtils;
import com.narwhal.basics.external.sendgrid.dto.endpoint.MailAddress;
import com.narwhal.basics.external.sendgrid.dto.endpoint.MailContent;
import com.narwhal.basics.external.sendgrid.dto.endpoint.MailPersonalizations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas de Priede
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailDTO implements Serializable {

    private List<MailPersonalizations> personalizations = new ArrayList<>();
    private String subject;
    private MailAddress from;
    @JsonProperty("reply_to")
    private MailAddress replyTo;
    private List<MailContent> content = new ArrayList<>();

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
