package com.narwhal.basics.core.rest.model.paging;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tomas de Priede
 */
public class PagingResultResponse implements Serializable {

    private String cursor;
    private Integer offset;
    private List resultList;

    public PagingResultResponse() {
    }

    public PagingResultResponse(String cursor, List resultList) {
        this.cursor = cursor;
        this.resultList = resultList;
    }

    public PagingResultResponse(Integer offset, List resultList) {
        this.offset = offset;
        this.resultList = resultList;
    }

    public String getCursor() {
        return cursor;
    }

    public List getResultList() {
        return resultList;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PagingResultResponse response = (PagingResultResponse) o;
        if (cursor != null ? !cursor.equals(response.cursor)
                : response.cursor != null)
            return false;
        if (offset != null ? !offset.equals(response.offset)
                : response.offset != null)
            return false;
        return !(resultList != null ? !resultList.equals(response.resultList)
                : response.resultList != null);
    }

    @Override
    public int hashCode() {
        int result = cursor != null ? cursor.hashCode() : 0;
        result = 31 * result + (offset != null ? offset.hashCode() : 0);
        result = 31 * result + (resultList != null ? resultList.hashCode() : 0);
        return result;
    }
}
