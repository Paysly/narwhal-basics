package com.narwhal.basics.core.rest.model;

public interface BaseModelUpdatable<T extends BaseModel> extends BaseModel {
    boolean requiresUpdate(T dto);

    void update(T dto);

    int getSort();
}
