package com.narwhal.basics.core.jobs.model;


import com.narwhal.basics.core.rest.model.paging.PagingResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomyair on 8/19/17.
 */
@Data
@NoArgsConstructor
public class JobPagingResult<T extends Object> implements Serializable {
    private int readEntities;
    private int processedEntities;
    private String cursor;
    private List<T> resultList;

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
}