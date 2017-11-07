package com.narwhal.basics.external.core.services;

import com.narwhal.basics.core.rest.exceptions.EntityNotFoundException;
import com.narwhal.basics.core.rest.exceptions.api.BadRequestException;
import com.narwhal.basics.core.rest.exceptions.api.NotFoundException;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.external.core.dao.ApplicationSettingsDao;
import com.narwhal.basics.external.core.model.ApplicationSettings;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

public class ApplicationSettingsServiceTest {

    @InjectMocks
    private ApplicationSettingsService applicationSettingsService;
    @Mock
    private MicroservicesContext microservicesContext;

    @Mock
    private ApplicationSettingsDao settingsDao;

    @Before
    public void setUp() {
        applicationSettingsService = new ApplicationSettingsService();
        MockitoAnnotations.initMocks(this);
        //
        when(microservicesContext.getApplicationSettingsId()).thenReturn("applicationSettingsId");
    }

    @Test
    public void test_getApplicationSettings_FoundSetting() throws EntityNotFoundException {
        //
        String applicationSettingsId = "applicationSettingsId";
        //
        ApplicationSettings applicationSettingsExpected = new ApplicationSettings();
        applicationSettingsExpected.init(applicationSettingsId);
        applicationSettingsExpected.setEmailSender("email");
        applicationSettingsExpected.setFirebaseAppUrl("urlfire");
        applicationSettingsExpected.setFirebaseMessagingUrl("firabasemess");
        applicationSettingsExpected.setFirebaseServerKey("serverkey");
        //
        when(settingsDao.get(ApplicationSettings.class, applicationSettingsId)).thenReturn(applicationSettingsExpected);
        //
        ApplicationSettings settings = applicationSettingsService.getApplicationSettings();
        assertEquals(applicationSettingsExpected, settings);
        //
        Mockito.verify(settingsDao, never()).save(applicationSettingsExpected);
    }

    @Test(expected = NotFoundException.class)
    public void test_getApplicationSettings_NotFoundSetting() throws EntityNotFoundException {
        //
        String applicationSettingsId = "applicationSettingsId";
        //
        ApplicationSettings applicationSettingsExpected = new ApplicationSettings();
        //
        when(settingsDao.get(ApplicationSettings.class, applicationSettingsId)).thenThrow(NotFoundException.class);
        //
        ApplicationSettings settings = applicationSettingsService.getApplicationSettings();
        assertEquals(applicationSettingsExpected, settings);
        //
        Mockito.verify(settingsDao).save(applicationSettingsExpected);
    }

    @Test
    public void test_updateApplicationSettings() {
        //
        String applicationSettingsId = "applicationSettingsId";
        //
        ApplicationSettings applicationSettingsExpected = new ApplicationSettings();
        applicationSettingsExpected.init(applicationSettingsId);
        applicationSettingsExpected.setEmailSender("email");
        applicationSettingsExpected.setFirebaseAppUrl("urlfire");
        applicationSettingsExpected.setFirebaseMessagingUrl("firabasemess");
        applicationSettingsExpected.setFirebaseServerKey("serverkey");
        //
        applicationSettingsService.updateApplicationSettings(applicationSettingsExpected);
        //
        Mockito.verify(settingsDao).save(applicationSettingsExpected);
    }

    @Test(expected = BadRequestException.class)
    public void test_updateApplicationSettings_nullSettings() {
        //
        ApplicationSettings applicationSettingsExpected = null;
        //
        applicationSettingsService.updateApplicationSettings(applicationSettingsExpected);
        //
        Mockito.verify(settingsDao, never()).save(applicationSettingsExpected);
    }
}
