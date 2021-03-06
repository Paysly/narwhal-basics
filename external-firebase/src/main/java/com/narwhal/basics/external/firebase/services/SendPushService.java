package com.narwhal.basics.external.firebase.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.core.model.FirebaseSettings;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.external.core.utils.EnvironmentContext;
import com.narwhal.basics.external.firebase.dto.FirebaseCloudMessageResponse;
import com.narwhal.basics.external.firebase.dto.FirebasePayload;
import com.narwhal.basics.external.firebase.dto.PushResponse;
import com.narwhal.basics.external.firebase.dto.SendPushMessage;
import com.narwhal.basics.external.firebase.endpoint.FirebaseCloudMessagingEndpoint;
import com.narwhal.basics.external.firebase.exceptions.InvalidFirebaseTokenException;
import com.narwhal.basics.external.firebase.exceptions.PushNotSendException;
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
public class SendPushService {



    @Inject
    private VelocityEngine velocityEngine;
    @Inject
    private FirebaseCloudMessagingEndpoint firebaseCloudMessagingApi;
    @Inject
    private ApplicationSettingsCachedService cachedService;

    public PushResponse sendPush(ApplicationEnvironmentTypes environment, SendPushMessage pushMessage) {
        ApiPreconditions.checkNotNull(environment, "environment");
        log.log(Level.INFO, "Task to send sms in progress");
        //
        RenderTool renderTool = new RenderTool();
        renderTool.setVelocityEngine(velocityEngine);
        renderTool.setCatchExceptions(false);
        //
        try {
            FirebaseSettings firebaseSettings = cachedService.getCachedApplicationSettings(environment);
            firebaseSettings.checkFirebaseData();
            //
            Context map = new VelocityContext();
            map.put("ctx", new EnvironmentContext(firebaseSettings.getFirebaseAppUrl()));
            map.put("model", pushMessage.getModel());
            //
            String title = renderTool.eval(map, pushMessage.getTitle());
            String body = renderTool.eval(map, pushMessage.getBody());
            //
            FirebasePayload payload = new FirebasePayload();
            payload.setTitle(title);
            payload.setBody(body);
            payload.setClickAction(firebaseSettings.getFirebaseAppUrl() + pushMessage.getPushActionLink());
            payload.setIcon(firebaseSettings.getFirebaseIconUrl());
            payload.setData(pushMessage.getModel());
            //
            FirebaseCloudMessageResponse response = firebaseCloudMessagingApi.sendMessage(environment,
                    pushMessage.getTo(), payload);
            //
            if (response.getSuccess() == 0) {
                throw new InvalidFirebaseTokenException(pushMessage.getTo());
            }
            //
            PushResponse pushResponse = new PushResponse();
            pushResponse.init(title, body);
            return pushResponse;
            //
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to render push template", e);
            throw new PushNotSendException(e.getMessage(), e);
        }
    }
}
