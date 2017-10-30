package com.narwhal.basics.external.core.model;

import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.core.rest.model.BaseModelUpdatable;

public interface EnvironmentVariableUpdatable extends BaseModel {

    int getSort();

    String getValue();
}
