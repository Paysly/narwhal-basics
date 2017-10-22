package com.narwhal.basics.integrations.authorization.client.dao;

import com.google.inject.ImplementedBy;
import com.narwhal.basics.core.rest.daos.BaseDao;
import com.narwhal.basics.integrations.authorization.client.model.ApplicationToken;

@ImplementedBy(ApplicationTokenDaoObjectifyImpl.class)
public interface ApplicationTokenDao extends BaseDao<ApplicationToken> {
}
