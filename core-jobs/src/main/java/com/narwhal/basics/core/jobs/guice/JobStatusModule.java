package com.narwhal.basics.core.jobs.guice;


import com.narwhal.basics.core.jobs.model.JobStatus;
import com.narwhal.basics.core.rest.guice.SubModule;
import com.narwhal.basics.core.rest.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas de Priede
 */
public class JobStatusModule extends SubModule {

    @Override
    public List<Class<? extends BaseModel>> objectifyClasses() {
        List<Class<? extends BaseModel>> list = new ArrayList<>();
        //
        list.add(JobStatus.class);
        //
        return list;
    }
}
