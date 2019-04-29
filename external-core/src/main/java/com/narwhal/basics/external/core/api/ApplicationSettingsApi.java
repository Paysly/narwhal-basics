package com.narwhal.basics.external.core.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.core.dto.FirebaseDataDTO;
import com.narwhal.basics.external.core.dto.SendgridDataDTO;
import com.narwhal.basics.external.core.dto.TwilioDataDTO;
import com.narwhal.basics.external.core.model.ApplicationSettings;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.integrations.authorization.client.dto.SuccessTokenDTO;
import com.narwhal.basics.integrations.authorization.client.services.AuthorizationService;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationScopeTypes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/v1/application/settings/")
public class ApplicationSettingsApi {


    @Inject
    private ApplicationSettingsCachedService cachedService;
    @Inject
    private AuthorizationService authorizationService;

    @GET
    @Path("/sendgrid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSendgridData(@HeaderParam("Auth") String authHeader) {
        ApiPreconditions.checkNotNull(authHeader, "authHeader");
        SuccessTokenDTO tokenDTO = authorizationService.validateToken(authHeader, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS);
        //
        ApplicationSettings settings = cachedService.getCachedApplicationSettings(tokenDTO.getEnvironment());
        SendgridDataDTO dataDTO = new SendgridDataDTO();
        //
        dataDTO.setEmailSender(settings.getEmailSender());
        dataDTO.setApiKey(settings.getSendgridApiKey());
        dataDTO.setMailUrl(settings.getSendgridMailUrl());
        dataDTO.setAppUrl(settings.getSendgridAppUrl());
        //
        return Response.ok(dataDTO).build();
    }

    @POST
    @Path("/sendgrid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSendgridData(@HeaderParam("Auth") String authHeader,
                                       SendgridDataDTO sendgridDataDTO) {
        ApiPreconditions.checkNotNull(authHeader, "authHeader");
        SuccessTokenDTO tokenDTO = authorizationService.validateToken(authHeader, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS);
        //
        cachedService.updateSendgridData(tokenDTO.getEnvironment(), sendgridDataDTO);
        //
        return Response.ok().build();
    }

    @GET
    @Path("/twilio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTwilioData(@HeaderParam("Auth") String authHeader) {
        ApiPreconditions.checkNotNull(authHeader, "authHeader");
        SuccessTokenDTO tokenDTO = authorizationService.validateToken(authHeader, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS);
        ApplicationSettings settings = cachedService.getCachedApplicationSettings(tokenDTO.getEnvironment());
        //
        TwilioDataDTO dataDTO = new TwilioDataDTO();
        //
        dataDTO.setFromNumber(settings.getTwilioFromNumber());
        dataDTO.setSid(settings.getTwilioSid());
        dataDTO.setToken(settings.getTwilioToken());
        dataDTO.setUrl(settings.getTwilioUrl());
        //
        return Response.ok(dataDTO).build();
    }

    @POST
    @Path("/twilio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTwilioData(@HeaderParam("Auth") String authHeader,
                                     TwilioDataDTO twilioDataDTO) {
        ApiPreconditions.checkNotNull(authHeader, "authHeader");
        SuccessTokenDTO tokenDTO = authorizationService.validateToken(authHeader, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS);
        cachedService.updateTwilioData(tokenDTO.getEnvironment(), twilioDataDTO);
        //
        return Response.ok().build();
    }

    @GET
    @Path("/firebase")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFirebaseData(@HeaderParam("Auth") String authHeader) {
        ApiPreconditions.checkNotNull(authHeader, "authHeader");
        SuccessTokenDTO tokenDTO = authorizationService.validateToken(authHeader, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS);
        ApplicationSettings settings = cachedService.getCachedApplicationSettings(tokenDTO.getEnvironment());
        //
        FirebaseDataDTO dataDTO = new FirebaseDataDTO();
        //
        dataDTO.setAppUrl(settings.getFirebaseAppUrl());
        dataDTO.setIconUrl(settings.getFirebaseIconUrl());
        dataDTO.setMessagingUrl(settings.getFirebaseMessagingUrl());
        dataDTO.setServerKey(settings.getFirebaseServerKey());
        dataDTO.setIosKey(settings.getFirebaseIosKey());
        dataDTO.setAndroidKey(settings.getFirebaseAndroidKey());
        //
        return Response.ok(dataDTO).build();
    }

    @POST
    @Path("/firebase")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFirebaseData(@HeaderParam("Auth") String authHeader,
                                       FirebaseDataDTO firebaseDataDTO) {
        ApiPreconditions.checkNotNull(authHeader, "authHeader");
        SuccessTokenDTO tokenDTO = authorizationService.validateToken(authHeader, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS);
        cachedService.updateFirebaseData(tokenDTO.getEnvironment(), firebaseDataDTO);
        //
        return Response.ok().build();
    }
}
