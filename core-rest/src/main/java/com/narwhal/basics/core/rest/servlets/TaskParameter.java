package com.narwhal.basics.core.rest.servlets;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * User: Tomas de Priede Date: 6/2/14 Time: 8:44 PM
 */
public class TaskParameter {

    private String name;
    private String value;

    public TaskParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        TaskParameter rhs = (TaskParameter) obj;
        return new EqualsBuilder().append(name, rhs.name).append(value, rhs.value).isEquals();
    }
}
