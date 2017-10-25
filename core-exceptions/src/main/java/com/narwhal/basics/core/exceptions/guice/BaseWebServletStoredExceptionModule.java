package com.narwhal.basics.core.exceptions.guice;

import com.narwhal.basics.core.exceptions.mapper.StoredApiExceptionMapper;
import com.narwhal.basics.core.rest.guice.BaseWebServletModule;
import com.narwhal.basics.core.rest.guice.SubModule;

import javax.ws.rs.ext.ExceptionMapper;

public abstract class BaseWebServletStoredExceptionModule extends BaseWebServletModule {

    public BaseWebServletStoredExceptionModule(SubModule... servletModuleList) {
        super(servletModuleList);
    }

    @Override
    protected Class<? extends ExceptionMapper> bindExceptionMapper() {
        return StoredApiExceptionMapper.class;
    }
}
