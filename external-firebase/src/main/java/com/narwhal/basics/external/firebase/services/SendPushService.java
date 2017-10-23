package com.narwhal.basics.external.firebase.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.core.model.FirebaseSettings;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.external.firebase.dto.FirebaseCloudMessageResponse;
import com.narwhal.basics.external.firebase.dto.FirebasePayload;
import com.narwhal.basics.external.firebase.dto.SendPushMessage;
import com.narwhal.basics.external.firebase.endpoint.FirebaseCloudMessagingEndpoint;
import com.narwhal.basics.external.firebase.exceptions.InvalidFirebaseTokenException;
import com.narwhal.basics.external.firebase.exceptions.PushNotSendException;
import com.narwhal.basics.external.core.utils.EnvironmentContext;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.generic.RenderTool;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomas de Priede
 */
@Singleton
public class SendPushService {

    private Logger logger = Logger.getLogger(SendPushService.class.getSimpleName());

    @Inject
    private VelocityEngine velocityEngine;
    @Inject
    private FirebaseCloudMessagingEndpoint firebaseCloudMessagingApi;
    @Inject
    private ApplicationSettingsCachedService cachedService;

    public void sendPush(String namespaceId, SendPushMessage pushMessage) {
        ApiPreconditions.checkNotNull(namespaceId, "namespaceId");
        logger.log(Level.INFO, "Task to send sms in progress");
        //
        RenderTool renderTool = new RenderTool();
        renderTool.setVelocityEngine(velocityEngine);
        renderTool.setCatchExceptions(false);
        //
        try {
            FirebaseSettings firebaseSettings = cachedService.getCachedApplicationSettings(namespaceId);
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
            FirebaseCloudMessageResponse response = firebaseCloudMessagingApi.sendMessage(namespaceId,
                    pushMessage.getTo(), payload);
            //
            if (response.getSuccess() == 0) {
                throw new InvalidFirebaseTokenException(pushMessage.getTo());
            }
            //
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to render push template", e);
            throw new PushNotSendException(e.getMessage(), e);
        }
    }
}
