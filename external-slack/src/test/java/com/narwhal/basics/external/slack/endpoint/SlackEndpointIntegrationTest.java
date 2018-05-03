package com.narwhal.basics.external.slack.endpoint;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalURLFetchServiceTestConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.utils.SharedConstants;
import com.narwhal.basics.external.slack.model.SlackMessage;
import com.narwhal.basics.external.slack.model.SlackResponse;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;

/**
 * @author Tomas de Priede
 */
public class SlackEndpointIntegrationTest {

    //
    private SlackEndpoint messageAPI;
    private String token = "";
    private static final LocalURLFetchServiceTestConfig urlConfig = new LocalURLFetchServiceTestConfig();
    private static final LocalServiceTestHelper helper = new LocalServiceTestHelper(urlConfig);

    @Before
    public void setUp() throws Exception {
        helper.setUp();
        SharedConstants.junit = true;
        //
        MockitoAnnotations.initMocks(this);
        //
        //
        messageAPI = new SlackEndpoint();
        ApiFetchService apiFetchService = new ApiFetchService();
        //
        apiFetchService.setUrlFetchService(
                URLFetchServiceFactory.getURLFetchService());
        messageAPI.setApiFetchService(apiFetchService);
    }

    @After
    public void tearDown() throws Exception {
        helper.tearDown();
    }

    @Test
    public void test_sendMessage_valid() {
        //
        String channel = "general";
        String text = "Dolar hoy - ?? ";
        //
        SlackMessage message = SlackMessage.builder() //
                .channel(channel) //
                .text(text) //
                .build();
        //
        message.setToken(token);
        // Send Message
//        SlackResponse response = messageAPI
//                .sendMessage(ApplicationEnvironmentTypes.development, message);
//        Assert.assertNotNull(response);
//        Assert.assertTrue(response.isOk());
    }
}
