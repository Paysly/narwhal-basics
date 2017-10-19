package com.narwhal.basics.core.jobs.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomyair on 8/22/17.
 */
public class JobNames implements Serializable {

    private List<String> names = new ArrayList<>();

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
