package com.narwhal.basics.core.exceptions.guice;


import com.narwhal.basics.core.exceptions.model.StoredException;
import com.narwhal.basics.core.rest.guice.SubModule;
import com.narwhal.basics.core.rest.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class StoredExceptionModule extends SubModule {

    @Override
    public List<Class<? extends BaseModel>> objectifyClasses() {
        List<Class<? extends BaseModel>> list = new ArrayList<>();
        //
        list.add(StoredException.class);
        //
        return list;
    }

    @Override
    public List<Class<? extends BaseModel>> supportedIndexClasses() {
        return objectifyClasses();
    }
}