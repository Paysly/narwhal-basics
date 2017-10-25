package com.narwhal.basics.integrations.authorization.client.cron;

import com.narwhal.basics.core.rest.guice.Cron;
import com.narwhal.basics.core.rest.guice.RelativePath;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.integrations.authorization.client.model.ApplicationToken;
import com.narwhal.basics.integrations.authorization.client.services.AuthorizationService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Cron
@RelativePath("/application/token/refresh")
public class ApplicationTokenRefreshCron extends HttpServlet {
    @Inject
    private Logger logger;
    @Inject
    private AuthorizationService authorizationService;
    @Inject
    private MicroservicesContext microservicesContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.log(Level.INFO, String.format("Cron %s started", this.getClass().getSimpleName()));
        //
        for (Map.Entry<String, String> clientIdSecret : microservicesContext.getClientIdSecret().entrySet()) {
            String clientId = clientIdSecret.getKey();
            //
            logger.log(Level.INFO, String.format("Refreshing token with clientId: %s ", clientId));
            ApplicationToken applicationToken = authorizationService.renewApplicationToken(clientId);
            logger.log(Level.INFO, String.format("Token with id: %s refreshed", applicationToken.getId()));
        }
        //
    }
}
