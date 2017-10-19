package com.narwhal.basics.core.jobs.model;


import com.narwhal.basics.core.rest.model.paging.PagingResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomyair on 8/19/17.
 */
public class JobPagingResult<T extends Object> implements Serializable {
    private int readEntities;
    private int processedEntities;
    private String cursor;
    private List<T> resultList;

    public JobPagingResult() {
    }

    public JobPagingResult(PagingResult<T> pagingResult) {
        this.resultList = pagingResult.getResultList();
        this.cursor = pagingResult.getCursor();
        if (pagingResult.getResultList() != null) {
            this.readEntities = pagingResult.getResultList().size();
        } else {
            this.readEntities = 0;
        }
        this.processedEntities = 0;
    }

    public int getReadEntities() {
        return readEntities;
    }

    public void setReadEntities(int readEntities) {
        this.readEntities = readEntities;
    }

    public int getProcessedEntities() {
        return processedEntities;
    }

    public void setProcessedEntities(int processedEntities) {
        this.processedEntities = processedEntities;
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
}
