package com.narwhal.basics.external.twilio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.narwhal.basics.core.rest.utils.ToStringUtils;
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

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
