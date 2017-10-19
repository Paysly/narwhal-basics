package com.narwhal.basics.core.rest.daos;


import com.narwhal.basics.core.rest.model.BaseModel;
import com.narwhal.basics.core.rest.model.paging.PagingResult;

/**
 * @author Tomas de Priede
 */
public interface PagingDatasource<T extends BaseModel> {

    /**
     * fetch an unfiltered page of T
     * 
     * @param clazz
     *            the class of T
     * @param pageSize
     *            the page size
     * @param cursor
     *            the location within the overall query results to return
     * @return
     */
    PagingResult<T> getPageOfAll(Class<T> clazz, Integer pageSize, String cursor);

    /**
     * fetch an unfiltered page of keys of T
     *
     * @param clazz
     *            the class of T
     * @param pageSize
     *            the page size
     * @param cursor
     *            the location within the overall query results to return
     * @return
     */
    PagingResult<String> getPageOfKeys(Class<T> clazz, Integer pageSize, String cursor);
}
