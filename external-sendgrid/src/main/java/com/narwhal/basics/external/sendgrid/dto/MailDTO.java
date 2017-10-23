package com.narwhal.basics.external.sendgrid.dto;

import com.fasterxml.jackson.annotation.*;
import com.narwhal.basics.core.rest.utils.ToStringUtils;
import com.narwhal.basics.external.sendgrid.dto.endpoint.MailAddress;
import com.narwhal.basics.external.sendgrid.dto.endpoint.MailContent;
import com.narwhal.basics.external.sendgrid.dto.endpoint.MailPersonalizations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas de Priede
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailDTO implements Serializable {

    private List<MailPersonalizations> personalizations = new ArrayList<>();
    private String subject;
    private MailAddress from;
    @JsonProperty("reply_to")
    private MailAddress replyTo;
    private List<MailContent> content = new ArrayList<>();

    public List<MailPersonalizations> getPersonalizations() {
        return personalizations;
    }

    public void setPersonalizations(List<MailPersonalizations> personalizations) {
        this.personalizations = personalizations;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MailAddress getFrom() {
        return from;
    }

    public void setFrom(MailAddress from) {
        this.from = from;
    }

    public List<MailContent> getContent() {
        return content;
    }

    public void setContent(List<MailContent> content) {
        this.content = content;
    }

    @JsonGetter
    public MailAddress getReplyTo() {
        return replyTo;
    }

    @JsonSetter
    public void setReplyTo(MailAddress replyTo) {
        this.replyTo = replyTo;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
