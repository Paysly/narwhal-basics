package com.narwhal.basics.core.exceptions.dao;

import com.google.inject.ImplementedBy;
import com.narwhal.basics.core.exceptions.model.StoredException;
import com.narwhal.basics.core.rest.daos.BaseDao;
import com.narwhal.basics.core.rest.model.paging.PagingResult;

@ImplementedBy(StoredExceptionDaoObjectifyImpl.class)
public interface StoredExceptionDao extends BaseDao<StoredException> {

    PagingResult<StoredException> getStoredExceptions();

    PagingResult<StoredException> getStoredExceptions(String searchNames);

    Integer getCountExceptionLast30Days(String exceptionName);
}
