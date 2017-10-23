package com.narwhal.basics.external.core.guice;

import com.narwhal.basics.core.rest.guice.SubModule;
import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.external.core.api.ApplicationSettingsApi;
import com.narwhal.basics.external.core.model.ApplicationSettings;

import java.util.ArrayList;
import java.util.List;

public class ApplicationSettingsModule extends SubModule {

    @Override
    public List<Class<? extends BaseModel>> objectifyClasses() {
        List<Class<? extends BaseModel>> list = new ArrayList<>();
        //
        list.add(ApplicationSettings.class);
        //
        return list;
    }

    @Override
    public List<Class<?>> apiClasses() {
        List<Class<?>> list = new ArrayList<>();
        //
        list.add(ApplicationSettingsApi.class);
        //
        return list;
    }
}
