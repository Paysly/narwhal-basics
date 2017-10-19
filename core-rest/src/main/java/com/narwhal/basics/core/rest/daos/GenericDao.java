package com.narwhal.basics.core.rest.daos;

import com.google.inject.ImplementedBy;

@ImplementedBy(GenericDaoObjectifyImpl.class)
public interface GenericDao extends BaseDao {

}
