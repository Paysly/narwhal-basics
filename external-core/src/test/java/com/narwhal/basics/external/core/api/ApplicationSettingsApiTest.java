package com.narwhal.basics.external.core.api;

import com.narwhal.basics.external.core.dto.FirebaseDataDTO;
import com.narwhal.basics.external.core.dto.SendgridDataDTO;
import com.narwhal.basics.external.core.dto.TwilioDataDTO;
import com.narwhal.basics.external.core.model.ApplicationSettings;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.integrations.authorization.client.dto.SuccessTokenDTO;
import com.narwhal.basics.integrations.authorization.client.services.AuthorizationService;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationScopeTypes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class ApplicationSettingsApiTest {

    @InjectMocks
    private ApplicationSettingsApi applicationSettingsApi;

    @Mock
    private ApplicationSettingsCachedService cachedService;
    @Mock
    private AuthorizationService authorizationService;

    @Before
    public void setUp() {
        applicationSettingsApi = new ApplicationSettingsApi();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getSendgridData() {
        //
        String auth = "auth";
        SuccessTokenDTO successTokenDTO = new SuccessTokenDTO();
        successTokenDTO.setId("123asd");
        successTokenDTO.setExpiration(220);
        successTokenDTO.setScopes(null);
        //
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.init("settingsId");
        applicationSettings.setEmailSender("email");
        applicationSettings.setSendgridApiKey("key");
        applicationSettings.setSendgridMailUrl("url");
        //
        SendgridDataDTO dataDTO = new SendgridDataDTO();
        //
        dataDTO.setEmailSender(applicationSettings.getEmailSender());
        dataDTO.setApiKey(applicationSettings.getSendgridApiKey());
        dataDTO.setMailUrl(applicationSettings.getSendgridMailUrl());
        //
        when(authorizationService.validateToken(auth, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS)).thenReturn(successTokenDTO);
        when(cachedService.getCachedApplicationSettings(successTokenDTO.getEnvironment())).thenReturn(applicationSettings);
        //
        Response response = applicationSettingsApi.getSendgridData(auth);
        assertEquals(dataDTO, response.getEntity());
    }

    @Test
    public void test_updateSendgridData() {
        //
        String auth = "auth";
        SuccessTokenDTO successTokenDTO = new SuccessTokenDTO();
        successTokenDTO.setId("123asd");
        successTokenDTO.setExpiration(220);
        successTokenDTO.setScopes(null);
        //
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.init("settingsId");
        applicationSettings.setEmailSender("email");
        applicationSettings.setSendgridApiKey("key");
        applicationSettings.setSendgridMailUrl("url");
        //
        SendgridDataDTO dataDTO = new SendgridDataDTO();
        //
        dataDTO.setEmailSender(applicationSettings.getEmailSender());
        dataDTO.setApiKey(applicationSettings.getSendgridApiKey());
        dataDTO.setMailUrl(applicationSettings.getSendgridMailUrl());
        //
        when(authorizationService.validateToken(auth, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS)).thenReturn(successTokenDTO);
        //
        applicationSettingsApi.updateSendgridData(auth, dataDTO);
    }

    @Test
    public void test_getTwilioData() {
        //
        String auth = "auth";
        SuccessTokenDTO successTokenDTO = new SuccessTokenDTO();
        successTokenDTO.setId("123asd");
        successTokenDTO.setExpiration(220);
        successTokenDTO.setScopes(null);
        //
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.init("settingsId");
        applicationSettings.setTwilioUrl("url");
        applicationSettings.setTwilioSid("sid");
        applicationSettings.setTwilioToken("token");
        applicationSettings.setTwilioFromNumber("number");
        //
        TwilioDataDTO dataDTO = new TwilioDataDTO();
        //
        dataDTO.setFromNumber(applicationSettings.getTwilioFromNumber());
        dataDTO.setSid(applicationSettings.getTwilioSid());
        dataDTO.setToken(applicationSettings.getTwilioToken());
        dataDTO.setUrl(applicationSettings.getTwilioUrl());
        //
        when(authorizationService.validateToken(auth, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS)).thenReturn(successTokenDTO);
        when(cachedService.getCachedApplicationSettings(successTokenDTO.getEnvironment())).thenReturn(applicationSettings);
        //
        Response response = applicationSettingsApi.getTwilioData(auth);
        assertEquals(dataDTO, response.getEntity());
    }

    @Test
    public void updateTwilioData() {
        //
        String auth = "auth";
        SuccessTokenDTO successTokenDTO = new SuccessTokenDTO();
        successTokenDTO.setId("123asd");
        successTokenDTO.setExpiration(220);
        successTokenDTO.setScopes(null);
        //
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.init("settingsId");
        applicationSettings.setTwilioUrl("url");
        applicationSettings.setTwilioSid("sid");
        applicationSettings.setTwilioToken("token");
        applicationSettings.setTwilioFromNumber("number");
        //
        TwilioDataDTO dataDTO = new TwilioDataDTO();
        //
        dataDTO.setFromNumber(applicationSettings.getTwilioFromNumber());
        dataDTO.setSid(applicationSettings.getTwilioSid());
        dataDTO.setToken(applicationSettings.getTwilioToken());
        dataDTO.setUrl(applicationSettings.getTwilioUrl());
        //
        when(authorizationService.validateToken(auth, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS)).thenReturn(successTokenDTO);
        //
        applicationSettingsApi.updateTwilioData(auth, dataDTO);
    }

    @Test
    public void test_getFirebaseData() {
        //
        String auth = "auth";
        SuccessTokenDTO successTokenDTO = new SuccessTokenDTO();
        successTokenDTO.setId("123asd");
        successTokenDTO.setExpiration(220);
        successTokenDTO.setScopes(null);
        //
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.init("settingsId");
        applicationSettings.setFirebaseAppUrl("url");
        applicationSettings.setFirebaseIconUrl("sid");
        applicationSettings.setFirebaseMessagingUrl("token");
        applicationSettings.setFirebaseServerKey("number");
        //
        FirebaseDataDTO dataDTO = new FirebaseDataDTO();
        //
        dataDTO.setAppUrl(applicationSettings.getFirebaseAppUrl());
        dataDTO.setIconUrl(applicationSettings.getFirebaseIconUrl());
        dataDTO.setMessagingUrl(applicationSettings.getFirebaseMessagingUrl());
        dataDTO.setServerKey(applicationSettings.getFirebaseServerKey());
        //
        when(authorizationService.validateToken(auth, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS)).thenReturn(successTokenDTO);
        when(cachedService.getCachedApplicationSettings(successTokenDTO.getEnvironment())).thenReturn(applicationSettings);
        //
        Response response = applicationSettingsApi.getFirebaseData(auth);
        assertEquals(dataDTO, response.getEntity());
    }

    @Test
    public void test_updateFirebaseData() {
        //
        String auth = "auth";
        SuccessTokenDTO successTokenDTO = new SuccessTokenDTO();
        successTokenDTO.setId("123asd");
        successTokenDTO.setExpiration(220);
        successTokenDTO.setScopes(null);
        //
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.init("settingsId");
        applicationSettings.setFirebaseAppUrl("url");
        applicationSettings.setFirebaseIconUrl("sid");
        applicationSettings.setFirebaseMessagingUrl("token");
        applicationSettings.setFirebaseServerKey("number");
        //
        FirebaseDataDTO dataDTO = new FirebaseDataDTO();
        //
        dataDTO.setAppUrl(applicationSettings.getFirebaseAppUrl());
        dataDTO.setIconUrl(applicationSettings.getFirebaseIconUrl());
        dataDTO.setMessagingUrl(applicationSettings.getFirebaseMessagingUrl());
        dataDTO.setServerKey(applicationSettings.getFirebaseServerKey());
        //
        when(authorizationService.validateToken(auth, ApplicationScopeTypes.NOTIFICATION_APPLICATION_SETTINGS)).thenReturn(successTokenDTO);
        //
        applicationSettingsApi.updateFirebaseData(auth, dataDTO);
    }

}
