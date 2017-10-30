package com.narwhal.basics.external.core.model;

import com.narwhal.basics.core.rest.model.BaseModelUpdatable;

public interface EnvironmentVariableUpdatable extends BaseModelUpdatable {

    int getSort();

    String getValue();
}
