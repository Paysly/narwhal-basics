package com.narwhal.basics.external.twilio.model;

import com.fasterxml.jackson.annotation.*;
import com.narwhal.basics.core.rest.utils.ToStringUtils;

import java.io.Serializable;


/**
 * @author Tomas de Priede
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwilioErrorResponse implements Serializable {

    private Integer code;
    private Integer status;
    private String message;
    @JsonProperty("more_info")
    private String moreInfo;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("more_info")
    @JsonGetter
    public String getMoreInfo() {
        return moreInfo;
    }

    @JsonProperty("more_info")
    @JsonSetter
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
