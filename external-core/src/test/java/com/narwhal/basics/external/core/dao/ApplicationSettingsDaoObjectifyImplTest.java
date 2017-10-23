package com.narwhal.basics.external.core.dao;


import com.narwhal.basics.core.rest.guice.SubModule;
import com.narwhal.basics.core.rest.testutils.BaseDaoTest;
import com.narwhal.basics.external.core.guice.ApplicationSettingsModule;
import com.narwhal.basics.external.core.model.ApplicationSettings;

public class ApplicationSettingsDaoObjectifyImplTest extends BaseDaoTest<ApplicationSettings, ApplicationSettingsDao> {


    public ApplicationSettingsDaoObjectifyImplTest() {
        super(ApplicationSettings.class, ApplicationSettingsDao.class);
    }


    @Override
    protected SubModule getGuiceSubModule() {
        return new ApplicationSettingsModule();
    }

    @Override
    protected ApplicationSettings newInstance() {
        ApplicationSettings applicationSettings = new ApplicationSettings();
        applicationSettings.init("app-id");
        applicationSettings.setEmailSender("email@sender.com");
        return applicationSettings;
    }
}
