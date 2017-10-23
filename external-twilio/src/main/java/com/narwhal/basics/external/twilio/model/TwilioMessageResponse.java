package com.narwhal.basics.external.twilio.model;

import com.fasterxml.jackson.annotation.*;
import com.narwhal.basics.core.rest.utils.ToStringUtils;

import java.io.Serializable;

/**
 * @author Tomas de Priede
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwilioMessageResponse implements Serializable {

    private String sid;
    private String accountSid;
    private String to;
    private String from;
    private String body;
    @JsonProperty("price_unit")
    private String priceUnit;
    private String uri;
    private String status;
    private String direction;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JsonGetter
    @JsonProperty("price_unit")
    public String getPriceUnit() {
        return priceUnit;
    }

    @JsonSetter
    @JsonProperty("price_unit")
    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
