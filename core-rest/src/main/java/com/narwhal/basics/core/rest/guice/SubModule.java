package com.narwhal.basics.core.rest.guice;

import com.google.inject.servlet.ServletModule;
import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.core.rest.servlets.TaskServlet;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tomas de Priede
 */
public abstract class SubModule extends ServletModule {

    public List<Class<?>> apiClasses() {
        return new ArrayList<>();
    }

    public List<Class<? extends BaseModel>> objectifyClasses() {
        return new ArrayList<>();
    }

    public List<Class<? extends TaskServlet>> taskClasses() {
        return new ArrayList<>();
    }

    public List<Class<? extends HttpServlet>> cronClasses() {
        return new ArrayList<>();
    }

    public List<Class<? extends BaseModel>> supportedIndexClasses() {
        return new ArrayList<>();
    }

    public Map<String, Class<?>> dailyCountServiceClasses() {
        return new HashMap<>();
    }
}
