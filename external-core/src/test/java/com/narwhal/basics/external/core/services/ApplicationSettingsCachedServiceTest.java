package com.narwhal.basics.external.core.services;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;
import com.narwhal.basics.core.rest.memcached.MemcachedService;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.external.core.dto.FirebaseDataDTO;
import com.narwhal.basics.external.core.dto.SendgridDataDTO;
import com.narwhal.basics.external.core.dto.TwilioDataDTO;
import com.narwhal.basics.external.core.model.ApplicationSettings;
import com.narwhal.basics.integrations.authorization.client.types.ApplicationEnvironmentTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

public class ApplicationSettingsCachedServiceTest {

    protected LocalServiceTestHelper helper;

    @InjectMocks
    private ApplicationSettingsCachedService applicationSettingsCachedService;

    @Mock
    private ApplicationSettingsService settingsService;
    @Mock
    private MemcachedService memcachedService;
    @Mock
    private MicroservicesContext microservicesContext;

    @Before
    public void setUp() {
        //
        applicationSettingsCachedService = new ApplicationSettingsCachedService();
        MockitoAnnotations.initMocks(this);
        helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
        helper.setUp();
        //
        when(microservicesContext.getApplicationSettingsId()).thenReturn("applicationSettingsId");
    }

    @After
    public void tearDown() {
        this.helper.tearDown();
    }

    @Test
    public void test_getCachedApplicationSettings_NotNullSettings() {
        //
        String applicationSettingsId = "applicationSettingsId";
        //
        ApplicationEnvironmentTypes environmentTypes = ApplicationEnvironmentTypes.development;
        //
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.setFirebaseServerKey("key");
        applicationSettings.setFirebaseMessagingUrl("url");
        applicationSettings.setFirebaseIconUrl("url");
        applicationSettings.init(applicationSettingsId);
        //
        when((ApplicationSettings) memcachedService.getFilteringByNamespace(environmentTypes.toString(), applicationSettingsId)).thenReturn(applicationSettings);
        //
        ApplicationSettings settings = applicationSettingsCachedService.getCachedApplicationSettings(environmentTypes);
        assertEquals(applicationSettings, settings);
        //
        Mockito.verify(memcachedService, never()).putFilteringByNamespace(environmentTypes.toString(), applicationSettingsId, settings);
    }

    @Test
    public void test_getCachedApplicationSettings_NullSettings() {
        //
        String applicationSettingsId = "applicationSettingsId";
        //
        ApplicationEnvironmentTypes environmentTypes = ApplicationEnvironmentTypes.development;
        //
        ApplicationSettings applicationSettings = null;
        //
        ApplicationSettings applicationSettings1 = new ApplicationSettings();
        applicationSettings1.setFirebaseServerKey("key");
        applicationSettings1.setFirebaseMessagingUrl("url");
        applicationSettings1.setFirebaseIconUrl("url");
        applicationSettings1.init(applicationSettingsId);
        //
        when((ApplicationSettings) memcachedService.getFilteringByNamespace(environmentTypes.toString(), applicationSettingsId)).thenReturn(applicationSettings);
        when(settingsService.getApplicationSettings()).thenReturn(applicationSettings1);
        //
        ApplicationSettings settings = applicationSettingsCachedService.getCachedApplicationSettings(environmentTypes);
        assertEquals(applicationSettings1, settings);
        //
        Mockito.verify(memcachedService).putFilteringByNamespace(environmentTypes.toString(), applicationSettingsId, settings);
    }

    @Test
    public void test_updateCachedApplicationSettings() {
        //
        String applicationSettingsId = "applicationSettingsId";
        //
        ApplicationEnvironmentTypes environment = ApplicationEnvironmentTypes.development;
        //
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.setFirebaseServerKey("key");
        applicationSettings.setFirebaseMessagingUrl("url");
        applicationSettings.setFirebaseIconUrl("url");
        applicationSettings.init(applicationSettingsId);
        //
        applicationSettingsCachedService.updateCachedApplicationSettings(environment, applicationSettings);
    }

    @Test(expected = BadRequestException.class)
    public void test_updateSendgridData_nameSpaceIdNull() {
        ApplicationEnvironmentTypes environment = null;
        //
        SendgridDataDTO dataDTO = new SendgridDataDTO();
        //
        dataDTO.setEmailSender("email");
        dataDTO.setApiKey("key");
        dataDTO.setMailUrl("url");
        //
        applicationSettingsCachedService.updateSendgridData(environment, dataDTO);
    }

    @Test(expected = BadRequestException.class)
    public void test_updateSendgridData_dtoNull() {
        ApplicationEnvironmentTypes environment = null;
        //
        SendgridDataDTO dataDTO = null;
        //
        applicationSettingsCachedService.updateSendgridData(environment, dataDTO);
    }

    @Test(expected = BadRequestException.class)
    public void test_updateTwilioData_nameSpaceIdNull() {
        ApplicationEnvironmentTypes environment = null;
        //
        TwilioDataDTO dataDTO = new TwilioDataDTO();
        //
        dataDTO.setFromNumber("number");
        dataDTO.setSid("sid");
        dataDTO.setToken("token");
        dataDTO.setUrl("url");
        //
        applicationSettingsCachedService.updateTwilioData(environment, dataDTO);
    }

    @Test(expected = BadRequestException.class)
    public void test_updateTwilioData_dtoNull() {
        ApplicationEnvironmentTypes environment = null;
        //
        TwilioDataDTO dataDTO = null;
        //
        applicationSettingsCachedService.updateTwilioData(environment, dataDTO);
    }

    @Test(expected = BadRequestException.class)
    public void test_updateFirebaseData_nameSpaceIdNull() {
        ApplicationEnvironmentTypes environment = null;
        //
        FirebaseDataDTO dataDTO = new FirebaseDataDTO();
        //
        dataDTO.setAppUrl("appurl");
        dataDTO.setIconUrl("iconurl");
        dataDTO.setMessagingUrl("messurl");
        dataDTO.setServerKey("server");
        //
        applicationSettingsCachedService.updateFirebaseData(environment, dataDTO);
    }

    @Test(expected = BadRequestException.class)
    public void test_updateFirebaseData_dtoNull() {
        ApplicationEnvironmentTypes environment = ApplicationEnvironmentTypes.development;
        //
        FirebaseDataDTO dataDTO = null;
        //
        applicationSettingsCachedService.updateFirebaseData(environment, dataDTO);
    }
}
