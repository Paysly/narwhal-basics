package com.narwhal.basics.integration.authorization.api.support;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalURLFetchServiceTestConfig;
import com.google.common.base.Joiner;
import com.narwhal.basics.core.rest.api.ApiFetchService;
import com.narwhal.basics.core.rest.utils.SharedConstants;
import com.narwhal.basics.integrations.authorization.client.api.AuthorizationApi;
import com.narwhal.basics.integrations.authorization.client.dto.Token;
import com.narwhal.basics.integrations.authorization.client.exceptions.InvalidCredentialsException;
import com.narwhal.basics.integrations.authorization.client.model.ApplicationToken;
import com.narwhal.basics.integrations.authorization.client.services.AuthorizationService;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationScopeTypes;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.MediaType;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public abstract class BaseEndpointIntegrationTest {

    protected static final LocalURLFetchServiceTestConfig urlConfig = new LocalURLFetchServiceTestConfig();
    protected static final LocalServiceTestHelper helper = new LocalServiceTestHelper(urlConfig);

    protected AuthorizationApi authorizationApi = new AuthorizationApi();
    protected ApplicationToken applicationToken;
    protected ApiFetchService apiFetchService = new ApiFetchService();

    @Mock
    protected AuthorizationService authorizationService;


    @Before
    public void setUp() throws Exception {
        helper.setUp();
        SharedConstants.junit = true;
        MockitoAnnotations.initMocks(this);
        //
        apiFetchService.setUrlFetchService(URLFetchServiceFactory.getURLFetchService());
        //
        initEndpoint();
    }

    protected abstract void initEndpoint();

    protected abstract String getAuthorizationEndpoint();


    @After
    public void tearDown() throws Exception {
        helper.tearDown();
    }

    /**
     * //TODO be more flexible in basics authorization api
     *
     * @return
     */
    protected void authorizeApplicationToken(String clientId, String clientSecret, ApplicationScopeTypes[] scopes) {
        //
        List<HTTPHeader> headerList = new ArrayList<>();
        headerList.add(new HTTPHeader("Content-type", MediaType.APPLICATION_JSON));
        //
        Map<String, String> params = new HashMap<>();
        params.put("clientKey", clientId);
        params.put("clientSecret", clientSecret);
        //
        try {
            params.put("scopes", URLEncoder.encode(Joiner.on("|").join(scopes), "UTF-8"));
            Token token = apiFetchService.fetch(getAuthorizationEndpoint(), HTTPMethod.GET,
                    headerList, params, Token.class);
            //
            applicationToken = new ApplicationToken();
            applicationToken.init("1", token.getJwt(), 100000);
            //
            when(authorizationService.getApplicationToken(eq(clientId))).thenReturn(applicationToken);
        } catch (Exception e) {
            throw new InvalidCredentialsException(e);
        }
//
    }
}
