package com.narwhal.basics.external.core.guice;

import com.narwhal.basics.core.rest.guice.SubModule;
import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.external.core.api.ApplicationSettingsApi;
import com.narwhal.basics.external.core.api.EnvironmentVariablesApi;
import com.narwhal.basics.external.core.model.ApplicationSettings;
import com.narwhal.basics.external.core.model.EnvironmentVariable;

import java.util.ArrayList;
import java.util.List;

public class ApplicationSettingsModule extends SubModule {

    @Override
    public List<Class<? extends BaseModel>> objectifyClasses() {
        List<Class<? extends BaseModel>> list = new ArrayList<>();
        //
        list.add(EnvironmentVariable.class);
        list.add(ApplicationSettings.class);
        //
        return list;
    }

    @Override
    public List<Class<?>> apiClasses() {
        List<Class<?>> list = new ArrayList<>();
        //
        list.add(ApplicationSettingsApi.class);
        list.add(EnvironmentVariablesApi.class);
        //
        return list;
    }
}
