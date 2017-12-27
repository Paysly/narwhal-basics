package com.narwhal.basics.external.twilio.model;

import com.fasterxml.jackson.annotation.*;
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
public class TwilioErrorResponse implements Serializable {

    private Integer code;
    private Integer status;
    private String message;
    @JsonProperty("more_info")
    private String moreInfo;

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
