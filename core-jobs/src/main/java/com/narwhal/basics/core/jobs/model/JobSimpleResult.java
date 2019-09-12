package com.narwhal.basics.core.jobs.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tomyair on 8/19/17.
 */
@Data
@NoArgsConstructor
public class JobSimpleResult<T extends Object> implements Serializable {

    private int readEntities;
    private int processedEntities;
    private List<T> resultList;

    public JobSimpleResult(List<T> entities) {
        this.resultList = entities;
        if (entities != null) {
            this.readEntities = entities.size();
        } else {
            this.readEntities = 0;
        }
        this.processedEntities = 0;
    }
}