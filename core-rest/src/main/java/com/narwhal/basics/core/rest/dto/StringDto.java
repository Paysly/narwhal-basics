package com.narwhal.basics.core.rest.dto;

import java.io.Serializable;

/**
 * Created by tomyair on 8/18/17.
 */
public class StringDto implements Serializable {

    private String value;

    public StringDto() {
    }

    public StringDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
