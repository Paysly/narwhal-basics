package com.narwhal.basics.integrations.authorization.client.dao;

import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.daos.BaseDaoObjectifyImpl;
import com.narwhal.basics.integrations.authorization.client.model.ApplicationToken;

@Singleton
public class ApplicationTokenDaoObjectifyImpl extends BaseDaoObjectifyImpl<ApplicationToken> implements ApplicationTokenDao {
}
