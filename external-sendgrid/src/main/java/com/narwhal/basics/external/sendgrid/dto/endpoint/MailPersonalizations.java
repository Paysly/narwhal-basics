package com.narwhal.basics.external.sendgrid.dto.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.narwhal.basics.core.rest.utils.ToStringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tomas de Priede
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailPersonalizations implements Serializable {

    private List<MailAddress> to;
    private List<MailAddress> cc;
    private List<MailAddress> bcc;

    public MailPersonalizations() {
    }

    public MailPersonalizations(MailAddress to) {
        this.to = Lists.newArrayList(to);
    }

    public List<MailAddress> getTo() {
        return to;
    }

    public void setTo(List<MailAddress> to) {
        this.to = to;
    }

    public List<MailAddress> getCc() {
        return cc;
    }

    public void setCc(List<MailAddress> cc) {
        this.cc = cc;
    }

    public List<MailAddress> getBcc() {
        return bcc;
    }

    public void setBcc(List<MailAddress> bcc) {
        this.bcc = bcc;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
