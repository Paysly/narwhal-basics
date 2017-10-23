package com.narwhal.basics.external.twilio.endpoint;

import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalURLFetchServiceTestConfig;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.utils.SharedConstants;
import com.narwhal.basics.external.core.model.ApplicationSettings;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.external.twilio.model.TwilioMessageContainerResponse;
import com.narwhal.basics.external.twilio.types.TwilioErrorCode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Tomas de Priede
 */
public class TwilioMessageEndpointIntegrationTest {

    //
    private static final String INVALID_NUMBER = "+15005550001";
    private static final String UNROUTABLE_TO_NUMBER = "+15005550002";
    private static final String REQUIRE_INTERNATIONAL_GRANTS_NUMBER = "+15005550003";
    private static final String BLACKLISTED_NUMBER = "+15005550004";
    private static final String NOT_OWNED_FROM_NUMBER = "+15005550007";
    private static final String FULL_QUEUE_FROM_NUMBER = "+15005550008";
    private static final String INCAPABLE_OF_SMS_NUMBER = "+15005550009";
    private static final String VALID_TO_NUMBER = "+15005550010";
    private TwilioMessageEndpoint messageAPI;
    private static final LocalURLFetchServiceTestConfig urlConfig = new LocalURLFetchServiceTestConfig();
    private static final LocalServiceTestHelper helper = new LocalServiceTestHelper(urlConfig);

    @Mock
    private ApplicationSettingsCachedService cachedService;

    @Before
    public void setUp() throws Exception {
        helper.setUp();
        SharedConstants.junit = true;
        //
        MockitoAnnotations.initMocks(this);
        //
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.setTwilioUrl("https://api.twilio.com/2010-04-01");
        applicationSettings.setTwilioSid("AC09b49676d3b0955aa6955029f7ece0a4");
        applicationSettings.setTwilioToken("3dc327e802d5cd81dd9b154b432f50fd");
        applicationSettings.setTwilioFromNumber("+15005550006");
        //
        when(cachedService.getCachedApplicationSettings("namespace1")).thenReturn(applicationSettings);
        //
        messageAPI = new TwilioMessageEndpoint();
        messageAPI.setCachedService(cachedService);
        ApiFetchService apiFetchService = new ApiFetchService();
        //
        apiFetchService.setUrlFetchService(URLFetchServiceFactory.getURLFetchService());
        messageAPI.setApiFetchService(apiFetchService);
    }

    @After
    public void tearDown() throws Exception {
        helper.tearDown();
    }

    @Test
    public void test_sendSMS_valid() {
        //
        String toNumber = VALID_TO_NUMBER;
        String description = "You are awesome";
        // Send SMS
        TwilioMessageContainerResponse response = messageAPI.sendSMS("namespace1", toNumber, description);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMessageResponse());
        assertEquals("queued", response.getMessageResponse().getStatus());
        Assert.assertTrue(response.isSuccess());
        //
        Assert.assertFalse(response.hasError());
        Assert.assertNull(response.getErrorResponse());
    }

    @Test
    public void test_sendSMS_invalidFromNumber() {
        //
        String fromNumber = INVALID_NUMBER;
        String toNumber = VALID_TO_NUMBER;
        String description = "You are awesome";
        // Send SMS
        TwilioMessageContainerResponse response = messageAPI.sendSMS("namespace1", fromNumber, toNumber, description);
        checkErrorResponse(response, TwilioErrorCode.INVALID_FROM_PHONE_NUMBER.getCode(),
                "The 'From' number +15005550001 is not a valid phone number, shortcode, or alphanumeric sender ID.");
    }

    @Test
    public void test_sendSMS_notOwnedFromNumber() {
        //
        String fromNumber = NOT_OWNED_FROM_NUMBER;
        String toNumber = VALID_TO_NUMBER;
        String description = "You are awesome";
        // Send SMS
        TwilioMessageContainerResponse response = messageAPI.sendSMS("namespace1", fromNumber, toNumber, description);
        checkErrorResponse(response, TwilioErrorCode.NOT_OWNED_PHONE_NUMBER.getCode(),
                "The From phone number +15005550007 is not a valid, SMS-capable inbound phone number or short code for your account.");
    }

    @Test
    public void test_sendSMS_fullQueueFromNumber() {
        //
        String fromNumber = FULL_QUEUE_FROM_NUMBER;
        String toNumber = VALID_TO_NUMBER;
        String description = "You are awesome";
        // Send SMS
        TwilioMessageContainerResponse response = messageAPI.sendSMS("namespace1", fromNumber, toNumber, description);
        checkErrorResponse(response, TwilioErrorCode.FULL_SMS_QUEUE.getCode(), "SMS queue is full.");
    }

    @Test
    public void test_sendSMS_invalidToNumber() {
        //
        String toNumber = INVALID_NUMBER;
        String description = "You are awesome";
        // Send SMS
        TwilioMessageContainerResponse response = messageAPI.sendSMS("namespace1", toNumber, description);
        checkErrorResponse(response, TwilioErrorCode.INVALID_TO_PHONE_NUMBER.getCode(), "The 'To' number +15005550001 is not a valid phone number.");
    }

    @Test
    public void test_sendSMS_unroutableToNumber() {
        //
        String toNumber = UNROUTABLE_TO_NUMBER;
        String description = "You are awesome";
        // Send SMS
        TwilioMessageContainerResponse response = messageAPI.sendSMS("namespace1", toNumber, description);
        checkErrorResponse(response, TwilioErrorCode.UNROUTABLE_PHONE_NUMBER.getCode(),
                "The 'To' phone number: +15005550002, is not currently reachable using the 'From' phone number: +15005550006 via SMS.");
    }

    @Test
    public void test_sendSMS_requireInternationalGrants() {
        //
        String toNumber = REQUIRE_INTERNATIONAL_GRANTS_NUMBER;
        String description = "You are awesome";
        // Send SMS
        TwilioMessageContainerResponse response = messageAPI.sendSMS("namespace1", toNumber, description);
        checkErrorResponse(response, TwilioErrorCode.ENABLE_INTERNATIONAL_GRANTS.getCode(),
                "Permission to send an SMS has not been enabled for the region indicated by the 'To' number: +15005550003.");
    }

    @Test
    public void test_sendSMS_blacklisted() {
        //
        String toNumber = BLACKLISTED_NUMBER;
        String description = "You are awesome";
        // Send SMS
        TwilioMessageContainerResponse response = messageAPI.sendSMS("namespace1", toNumber, description);
        checkErrorResponse(response, TwilioErrorCode.BLACKLISTED_NUMBER.getCode(), "The message From/To pair violates a blacklist rule.");
    }

    @Test
    public void test_sendSMS_incapableOfSMS() {
        //
        String toNumber = INCAPABLE_OF_SMS_NUMBER;
        String description = "You are awesome";
        // Send SMS
        TwilioMessageContainerResponse response = messageAPI.sendSMS("namespace1", toNumber, description);
        checkErrorResponse(response, TwilioErrorCode.INCAPABLE_OF_SMS_NUMBER.getCode(), "To number: +15005550009, is not a mobile number");
    }

    private void checkErrorResponse(TwilioMessageContainerResponse response, Integer errorCode, String errorMessage) {
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getErrorResponse());
        Assert.assertTrue(response.hasError());
        assertEquals(errorCode, response.getErrorResponse().getCode());
        assertEquals(new Integer(400), response.getErrorResponse().getStatus());
        assertEquals(errorMessage, response.getErrorResponse().getMessage());
        //
        Assert.assertFalse(response.isSuccess());
        Assert.assertNull(response.getMessageResponse());
    }
}
