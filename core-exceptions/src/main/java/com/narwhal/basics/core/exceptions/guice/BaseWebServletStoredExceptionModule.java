package com.narwhal.basics.core.exceptions.guice;

import com.narwhal.basics.core.exceptions.mapper.StoredApiExceptionMapper;
import com.narwhal.basics.core.rest.guice.BaseWebServletModule;

import javax.ws.rs.ext.ExceptionMapper;

public abstract class BaseWebServletStoredExceptionModule extends BaseWebServletModule {

    @Override
    protected Class<? extends ExceptionMapper> bindExceptionMapper() {
        return StoredApiExceptionMapper.class;
    }
}
