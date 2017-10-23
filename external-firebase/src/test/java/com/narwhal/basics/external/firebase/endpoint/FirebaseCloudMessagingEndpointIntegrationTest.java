package com.narwhal.basics.external.firebase.endpoint;

import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalURLFetchServiceTestConfig;
import com.google.inject.Inject;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.utils.SharedConstants;
import com.narwhal.basics.external.core.model.ApplicationSettings;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.external.firebase.dto.FirebaseCloudMessageResponse;
import com.narwhal.basics.external.firebase.dto.FirebasePayload;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


/**
 * @author Tomas de Priede
 */
public class FirebaseCloudMessagingEndpointIntegrationTest {

    private static final LocalURLFetchServiceTestConfig urlConfig = new LocalURLFetchServiceTestConfig();
    private static final LocalServiceTestHelper helper = new LocalServiceTestHelper(urlConfig);
    public static final String FIREBASE_ICON_URL = "/assets/images/logos/paysly-for-dark-background.png";
    public static final String APP_URL = "http://localhost:5555";
    @Inject
    private FirebaseCloudMessagingEndpoint messagingApi;
    @Mock
    private ApplicationSettingsCachedService cachedService;

    @Before
    public void setUp() throws Exception {
        helper.setUp();
        SharedConstants.junit = true;
        MockitoAnnotations.initMocks(this);
        //
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.setFirebaseServerKey("AAAAzTjBmuA:APA91bEpAw-Lxlba5dQq8XfYpj_IcDGxV7XTLmMQZ9DmJpJdcTwAS6kZVLUDZ1zHn78tsPpe1KwSX5xCpv3WDdZ3-6pR_oJzd3cBj5ugYgYbEONcRTrasDmD2PIRJd1kTrx45Bv7PiNk");
        applicationSettings.setFirebaseMessagingUrl("https://fcm.googleapis.com/fcm/send");
        applicationSettings.setFirebaseIconUrl(FIREBASE_ICON_URL);
        applicationSettings.setFirebaseAppUrl(APP_URL);
        //
        when(cachedService.getCachedApplicationSettings(eq("namespace1"))).thenReturn(applicationSettings);
        //
        //
        messagingApi = new FirebaseCloudMessagingEndpoint();
        messagingApi.setCachedService(cachedService);
        ApiFetchService apiFetchService = new ApiFetchService();
        //
        apiFetchService.setUrlFetchService(URLFetchServiceFactory.getURLFetchService());
        messagingApi.setApiFetchService(apiFetchService);
    }

    @After
    public void tearDown() throws Exception {
        helper.tearDown();
    }

    @Test
    public void test_sendMessage() {
        // Get user
        String to = "ejvUk7e88aY:";
        FirebasePayload payload;
        payload = new FirebasePayload();
        payload.setTitle("Hello Pays.ly");
        payload.setBody("This is a notification");
        payload.setIcon(FIREBASE_ICON_URL);
        payload.setClickAction(APP_URL + "/verify-identity/");
        Map<String, Object> data = new HashMap<>();
        data.put("score", "5x1");
        payload.setData(data);
        //
        FirebaseCloudMessageResponse response = messagingApi.sendMessage("namespace1", to, payload);
        Assert.assertNotNull(response);
        assertEquals(new Long(1), response.getFailure());
    }
}
