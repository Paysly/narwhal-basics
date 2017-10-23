package com.narwhal.basics.external.core.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.EntityNotFoundException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.core.rest.utils.MicroservicesContext;
import com.narwhal.basics.external.core.dao.ApplicationSettingsDao;
import com.narwhal.basics.external.core.model.ApplicationSettings;

@Singleton
public class ApplicationSettingsService {

    @Inject
    private ApplicationSettingsDao settingsDao;
    @Inject
    private MicroservicesContext microservicesContext;

    public ApplicationSettings getApplicationSettings() {
        ApplicationSettings settings;
        try {
            settings = settingsDao.get(ApplicationSettings.class, microservicesContext.getApplicationSettingsId());
        } catch (EntityNotFoundException e) {
            settings = new ApplicationSettings();
            settings.init(microservicesContext.getApplicationSettingsId());
            settingsDao.save(settings);
        }
        return settings;
    }

    public void updateApplicationSettings(ApplicationSettings applicationSettings) {
        ApiPreconditions.checkNotNull(applicationSettings, "applicationSettings");
        //
        settingsDao.save(applicationSettings);
    }
}
