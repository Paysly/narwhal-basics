package com.narwhal.basics.external.twilio.types;


import com.narwhal.basics.core.rest.utils.ApiPreconditions;

/**
 * @author Tomas de Priede
 */
public enum TwilioErrorCode {
    //
    INVALID_TO_PHONE_NUMBER(21211),
    //
    INVALID_FROM_PHONE_NUMBER(21212),
    //
    ENABLE_INTERNATIONAL_GRANTS(21408),
    //
    NOT_OWNED_PHONE_NUMBER(21606),
    //
    BLACKLISTED_NUMBER(21610),
    //
    FULL_SMS_QUEUE(21611),
    //
    UNROUTABLE_PHONE_NUMBER(21612),
    //
    INCAPABLE_OF_SMS_NUMBER(21614);

    private Integer code;

    TwilioErrorCode(Integer code) {
        this.code = code;
    }

    public static TwilioErrorCode fromCode(Integer code) {
        ApiPreconditions.checkNotNull(code, "code");
        for (TwilioErrorCode errorCode : values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode;
            }
        }
        return INCAPABLE_OF_SMS_NUMBER; // Default
    }

    public Integer getCode() {
        return code;
    }
}
