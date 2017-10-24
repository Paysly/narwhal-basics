package com.narwhal.basics.core.exceptions.dao;

import com.googlecode.objectify.cmd.Query;
import com.narwhal.basics.core.exceptions.model.StoredException;
import com.narwhal.basics.core.rest.daos.BaseDaoObjectifyImpl;
import com.narwhal.basics.core.rest.daos.OfyService;
import com.narwhal.basics.core.rest.model.paging.PagingResult;
import com.narwhal.basics.core.rest.utils.DateUtils;
import org.apache.commons.lang.StringUtils;

import javax.inject.Singleton;

@Singleton
public class StoredExceptionDaoObjectifyImpl extends BaseDaoObjectifyImpl<StoredException> implements StoredExceptionDao {

    public static final int PAGE_SIZE = 100;

    @Override
    public PagingResult<StoredException> getStoredExceptions() {
        return getStoredExceptions(null);
    }

    @Override
    public PagingResult<StoredException> getStoredExceptions(String searchNames) {
        //
        searchNames = StringUtils.lowerCase(searchNames);
        //
        Query query = OfyService.ofy().load().type(StoredException.class);
        //
        if (searchNames != null) {
            query = query.filter("searchNames >=", searchNames).filter("searchNames <", searchNames + LAST_UNICODE_CHARACTER).order("searchNames");
        }
        query = query.order("-createdAt");
        return getPagingResult(query, PAGE_SIZE, null);
    }

    @Override
    public Integer getCountExceptionLast30Days(String exceptionName) {
        //
        Query query = OfyService.ofy().load().type(StoredException.class).filter("exceptionName", exceptionName)
                .filter("createdAt >=", DateUtils.getDayAMonthAgo()).order("createdAt");
        //
        return getCount(StoredException.class, query);
    }
}
