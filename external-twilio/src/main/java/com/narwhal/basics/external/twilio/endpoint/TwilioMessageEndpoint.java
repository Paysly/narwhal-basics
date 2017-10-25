package com.narwhal.basics.external.twilio.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.inject.Inject;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.exceptions.api.client.HttpClientException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.core.model.ApplicationSettings;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.external.twilio.exception.InvalidTwilioErrorParsingException;
import com.narwhal.basics.external.twilio.model.TwilioErrorResponse;
import com.narwhal.basics.external.twilio.model.TwilioMessageContainerResponse;
import com.narwhal.basics.external.twilio.model.TwilioMessageResponse;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;
import org.apache.commons.codec.binary.Base64;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomas de Priede
 */
public class TwilioMessageEndpoint {

    private static final Logger logger = Logger.getLogger(TwilioMessageEndpoint.class.getSimpleName());
    private static final String MESSAGES_URL = "%s/Accounts/%s/SMS/Messages.json";
    @Inject
    private ApiFetchService apiFetchService;
    @Inject
    private ApplicationSettingsCachedService cachedService;

    public List<HTTPHeader> prepareHeaders(ApplicationEnvironmentTypes environment) {
        List<HTTPHeader> headers = new ArrayList<>();
        headers.add(new HTTPHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED));
        headers.add(new HTTPHeader("Accept", MediaType.APPLICATION_JSON));
        //
        ApplicationSettings applicationSettings = cachedService.getCachedApplicationSettings(environment);
        applicationSettings.checkTwilioData();
        //
        String auth = applicationSettings.getTwilioSid() + ":" + applicationSettings.getTwilioToken();
        auth = new String(Base64.encodeBase64(auth.getBytes()));
        headers.add(new HTTPHeader("Authorization", "Basic " + auth));
        return headers;
    }

    /**
     * Send a SMS to an user phone
     *
     * @param toNumber
     * @param description
     * @return
     */
    public TwilioMessageContainerResponse sendSMS(ApplicationEnvironmentTypes environment, String toNumber, String description) {
        ApiPreconditions.checkNotNull(environment, "environment");
        //
        ApplicationSettings applicationSettings = cachedService.getCachedApplicationSettings(environment);
        applicationSettings.checkTwilioData();
        //
        return sendSMS(environment, applicationSettings.getTwilioFromNumber(), toNumber, description);
    }

    /**
     * Send a SMS to an user phone
     *
     * @param fromNumber
     * @param toNumber
     * @param description
     * @return
     */
    public TwilioMessageContainerResponse sendSMS(ApplicationEnvironmentTypes environment, String fromNumber, String toNumber, String description) {
        ApiPreconditions.checkNotNull(environment, "environment");
        ApiPreconditions.checkNotNull(fromNumber, "fromNumber");
        ApiPreconditions.checkNotNull(toNumber, "toNumber");
        ApiPreconditions.checkNotNull(description, "description");
        //
        ApplicationSettings applicationSettings = cachedService.getCachedApplicationSettings(environment);
        applicationSettings.checkTwilioData();
        //
        //
        String url = String.format(MESSAGES_URL, applicationSettings.getTwilioUrl(), applicationSettings.getTwilioSid());
        //
        String requestId = UUID.randomUUID().toString();
        Map<String, String> params = new HashMap<>();
        params.put("From", fromNumber);
        params.put("To", toNumber);
        params.put("Body", description);
        //
        logSmsRequest(applicationSettings.getTwilioSid(), requestId, fromNumber, toNumber, description);
        //
        TwilioMessageContainerResponse response = new TwilioMessageContainerResponse();
        try {
            List<HTTPHeader> headers = prepareHeaders(environment);
            TwilioMessageResponse messageResponse = apiFetchService.fetch(url, HTTPMethod.POST, headers, params, TwilioMessageResponse.class);
            response.setMessageResponse(messageResponse);
        } catch (HttpClientException e) {
            response.setErrorResponse(parseHttpClientException(e));
        }
        logSmsResponse(applicationSettings.getTwilioSid(), requestId, response);
        return response;
    }

    private void logSmsRequest(String sid, String requestId, String fromNumber, String toNumber, String description) {
        //
        logger.log(Level.INFO, "********* SMS REQUEST *********");
        logger.log(Level.INFO, "Rid: " + requestId);
        logger.log(Level.INFO, "Sid: " + sid);
        logger.log(Level.INFO, "From: " + fromNumber);
        logger.log(Level.INFO, "To: " + toNumber);
        logger.log(Level.INFO, "Body: " + description);
        logger.log(Level.INFO, "****** END - SMS REQUEST ******");
        //
    }

    private void logSmsResponse(String sid, String requestId, TwilioMessageContainerResponse response) {
        //
        logger.log(Level.INFO, "******** SMS RESPONSE *********");
        logger.log(Level.INFO, "Rid: " + requestId);
        logger.log(Level.INFO, "Sid: " + sid);
        logger.log(Level.INFO, "Success: " + response.isSuccess());
        if (response.hasError()) {
            logger.log(Level.INFO, "Message: " + response.getErrorResponse().getMessage());
        }
        logger.log(Level.INFO, "****** END - SMS RESPONSE *****");
        //
    }

    protected TwilioErrorResponse parseHttpClientException(HttpClientException e) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            TwilioErrorResponse errorResponse = mapper.readValue(e.getObjectAsString(), TwilioErrorResponse.class);
            return errorResponse;
        } catch (IOException ex) {
            throw new InvalidTwilioErrorParsingException("Could not parse error in twilio", ex);
        }
    }

    public void setApiFetchService(ApiFetchService apiFetchService) {
        this.apiFetchService = apiFetchService;
    }

    public void setCachedService(ApplicationSettingsCachedService cachedService) {
        this.cachedService = cachedService;
    }
}
