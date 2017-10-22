package com.narwhal.basics.integrations.authorization.client.guice;

import com.narwhal.basics.core.rest.guice.SubModule;
import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.integrations.authorization.client.cron.ApplicationTokenRefreshCron;
import com.narwhal.basics.integrations.authorization.client.model.ApplicationToken;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationModule extends SubModule {

    @Override
    public List<Class<? extends BaseModel>> objectifyClasses() {
        List<Class<? extends BaseModel>> list = new ArrayList<>();
        //
        list.add(ApplicationToken.class);
        //
        return list;
    }

    @Override
    public List<Class<? extends HttpServlet>> cronClasses() {
        List<Class<? extends HttpServlet>> list = new ArrayList<>();
        //
        list.add(ApplicationTokenRefreshCron.class);
        //
        return list;
    }
}
