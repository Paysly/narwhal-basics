package com.narwhal.basics.core.health.guice;


import com.narwhal.basics.core.health.api.HealthApi;
import com.narwhal.basics.core.rest.guice.SubModule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas de Priede
 */
public class HealthStatusModule extends SubModule {

    @Override
    public List<Class<?>> apiClasses() {
        List<Class<?>> list = new ArrayList<>();
        //
        list.add(HealthApi.class);
        //
        return list;
    }
}
