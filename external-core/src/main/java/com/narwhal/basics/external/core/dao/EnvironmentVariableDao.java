package com.narwhal.basics.external.core.dao;

import com.google.inject.ImplementedBy;
import com.narwhal.basics.core.rest.daos.BaseDao;
import com.narwhal.basics.external.core.model.EnvironmentVariable;

/**
 * Created by tomyair on 8/18/17.
 */
@ImplementedBy(EnvironmentVariableDaoObjectifyImpl.class)
public interface EnvironmentVariableDao extends BaseDao<EnvironmentVariable> {

}
