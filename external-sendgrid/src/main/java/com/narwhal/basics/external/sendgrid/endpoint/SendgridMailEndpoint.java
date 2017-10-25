package com.narwhal.basics.external.sendgrid.endpoint;

import com.google.api.server.spi.config.Singleton;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.inject.Inject;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.SharedConstants;
import com.narwhal.basics.external.core.model.ApplicationSettings;
import com.narwhal.basics.external.core.model.SendgridSettings;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.external.sendgrid.dto.MailDTO;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomas de Priede
 */
@Singleton
public class SendgridMailEndpoint {

    private Logger logger = Logger.getLogger(SendgridMailEndpoint.class.getSimpleName());

    @Inject
    private ApiFetchService apiFetchService;

    @Inject
    private ApplicationSettingsCachedService settingsCachedService;

    private List<HTTPHeader> buildSecuredHeaders(ApplicationEnvironmentTypes environment) {
        ApiPreconditions.checkNotNull(environment, "environment");
        //
        SendgridSettings applicationSettings = settingsCachedService.getCachedApplicationSettings(environment);
        applicationSettings.checkSendgridData();
        //
        String apiKey = applicationSettings.getSendgridApiKey();
        //
        List<HTTPHeader> headers = new ArrayList<>();
        headers.add(new HTTPHeader("Content-Type", "application/json"));
        headers.add(new HTTPHeader("Authorization", "Bearer " + apiKey));
        return headers;
    }


    public void sendMail(ApplicationEnvironmentTypes environment, MailDTO mailDTO) {
        ApiPreconditions.checkNotNull(environment, "environment");
        if (SharedConstants.isRunningOnLocalServer()) {
            logger.log(Level.INFO, "Sending mail to log file");
            logger.log(Level.INFO, mailDTO.toString());
        } else {
            //
            ApplicationSettings applicationSettings = settingsCachedService.getCachedApplicationSettings(environment);
            applicationSettings.checkSendgridData();
            apiFetchService.fetch(applicationSettings.getSendgridMailUrl(), HTTPMethod.POST,
                    buildSecuredHeaders(environment), mailDTO, null);
        }
    }
}
