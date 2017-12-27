package com.narwhal.basics.external.twilio.model;


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
public class TwilioMessageContainerResponse implements Serializable {

    private TwilioMessageResponse messageResponse;
    private TwilioErrorResponse errorResponse;

    public boolean isSuccess() {
        return !hasError();
    }

    public boolean hasError() {
        return errorResponse != null;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
