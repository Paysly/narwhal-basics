package com.narwhal.basics.external.slack.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import com.google.api.server.spi.config.Singleton;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.inject.Inject;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.external.slack.model.SlackMessage;
import com.narwhal.basics.external.slack.model.SlackResponse;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;

/**
 * @author Tomas de Priede
 */
@Singleton
public class SlackEndpoint {

    private Logger logger = Logger
            .getLogger(SlackEndpoint.class.getSimpleName());
    private static final String MESSAGES_URL = "https://slack.com/api/chat.postMessage";
    @Inject
    private ApiFetchService apiFetchService;
    @Inject
    private ApplicationSettingsCachedService settingsCachedService;

    public List<HTTPHeader> prepareHeaders(
            ApplicationEnvironmentTypes environment) {
        List<HTTPHeader> headers = new ArrayList<>();
        headers.add(new HTTPHeader("Content-Type",
                MediaType.APPLICATION_FORM_URLENCODED));
        //
        return headers;
    }

    public SlackResponse sendMessage(ApplicationEnvironmentTypes environment,
            SlackMessage messageDTO) {
        ApiPreconditions.checkNotNull(environment, "environment");
        //
        Map<String, String> params = new HashMap<>();
        //
        params.put("token", messageDTO.getToken());
        params.put("channel", messageDTO.getChannel());
        params.put("text", messageDTO.getText());
        //
        SlackResponse response = apiFetchService.fetch(MESSAGES_URL,
                HTTPMethod.POST, prepareHeaders(environment), params,
                SlackResponse.class);
        //
        return response;
    }

    public void setApiFetchService(ApiFetchService apiFetchService) {
        this.apiFetchService = apiFetchService;
    }
}
