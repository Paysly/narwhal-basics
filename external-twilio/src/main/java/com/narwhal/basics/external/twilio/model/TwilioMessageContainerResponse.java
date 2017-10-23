package com.narwhal.basics.external.twilio.model;


import com.narwhal.basics.core.rest.utils.ToStringUtils;

import java.io.Serializable;


/**
 * @author Tomas de Priede
 */
public class TwilioMessageContainerResponse implements Serializable {

    private TwilioMessageResponse messageResponse;
    private TwilioErrorResponse errorResponse;

    public boolean isSuccess() {
        return !hasError();
    }

    public boolean hasError() {
        return errorResponse != null;
    }

    public TwilioMessageResponse getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(TwilioMessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    public TwilioErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(TwilioErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
