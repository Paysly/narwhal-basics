package com.narwhal.basics.external.twilio.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.twilio.dto.SendPhoneMessage;
import com.narwhal.basics.external.twilio.dto.SmsResponse;
import com.narwhal.basics.external.twilio.endpoint.TwilioMessageEndpoint;
import com.narwhal.basics.external.twilio.exception.InvalidPhoneNumberException;
import com.narwhal.basics.external.twilio.exception.InvalidTwilioErrorParsingException;
import com.narwhal.basics.external.twilio.exception.SMSServiceNotAvailableException;
import com.narwhal.basics.external.twilio.model.TwilioMessageContainerResponse;
import com.narwhal.basics.external.twilio.types.TwilioErrorCode;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;
import lombok.extern.java.Log;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.generic.RenderTool;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomas de Priede
 */
@Log
@Singleton
public class SendSmsService {

    private VelocityEngine velocityEngine;
    private TwilioMessageEndpoint twilioMessageApi;

    public SmsResponse sendSms(ApplicationEnvironmentTypes environmentTypes, SendPhoneMessage phoneMessage) {
        ApiPreconditions.checkNotNull(environmentTypes, "environmentTypes");
        log.log(Level.INFO, "Task to send sms in progress");
        //
        Context map = new VelocityContext();
        //map.put("ctx", new EnvironmentContext());
        map.put("model", phoneMessage.getModel());
        //
        RenderTool renderTool = new RenderTool();
        renderTool.setVelocityEngine(velocityEngine);
        renderTool.setCatchExceptions(false);
        //
        try {
            String description = renderTool.eval(map, phoneMessage.getSubject());
            //
            TwilioMessageContainerResponse response = twilioMessageApi.sendSMS(environmentTypes, phoneMessage.getTo(), description);
            //
            if (response.hasError()) {
                //
                String message = "";
                TwilioErrorCode errorCode = TwilioErrorCode.fromCode(response.getErrorResponse().getCode());
                switch (errorCode) {
                    case ENABLE_INTERNATIONAL_GRANTS:
                        message = "Only US numbers are supported";
                        break;
                    case BLACKLISTED_NUMBER:
                        message = "Your number is blacklisted";
                        break;
                    case INVALID_TO_PHONE_NUMBER:
                        message = "Your number is invalid";
                        break;
                    case INCAPABLE_OF_SMS_NUMBER:
                        message = "Your number cannot receive SMS";
                        break;
                    case UNROUTABLE_PHONE_NUMBER:
                        message = "We cannot reach your number right now";
                        break;
                    case FULL_SMS_QUEUE:
                        message = "SMS Queue is full. Try again later";
                        throw new SMSServiceNotAvailableException(message);
                }
                throw new InvalidPhoneNumberException(message);
            }
            //
            SmsResponse sms = new SmsResponse();
            sms.init(description);
            return sms;
            //
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to render sms template", e);
            throw new InvalidTwilioErrorParsingException(e.getMessage(), e);
        }
    }

    @Inject
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Inject
    public void setTwilioMessageApi(TwilioMessageEndpoint twilioMessageApi) {
        this.twilioMessageApi = twilioMessageApi;
    }
}
