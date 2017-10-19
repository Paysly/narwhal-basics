package com.narwhal.basics.core.rest.model.paging;


import com.narwhal.basics.core.rest.utils.ToStringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a page of data. There are two fields combined to represent a page
 * of data: <br />
 * 1. cursor: a string cursor necesary for accessing the next page of data <br />
 * 2. resultList: a list of data of the current page
 *
 * @param <T>
 * @author Tomas de Priede
 */
@SuppressWarnings("serial")
public class PagingResult<T extends Object> implements Serializable {

    private String cursor;
    private List<T> resultList;

    public PagingResult() {
        super();
    }

    public PagingResult(String cursor, List<T> resultList) {
        super();
        this.cursor = cursor;
        this.resultList = resultList;
    }

    public PagingResult(PagingResult<T> result) {
        this(result.cursor, result.resultList);
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PagingResult<?> that = (PagingResult<?>) o;
        if (cursor != null ? !cursor.equals(that.cursor) : that.cursor != null)
            return false;
        return !(resultList != null ? !resultList.equals(that.resultList)
                : that.resultList != null);
    }

    @Override
    public int hashCode() {
        int result = cursor != null ? cursor.hashCode() : 0;
        result = 31 * result + (resultList != null ? resultList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ToStringUtils.toString(this);
    }
}
