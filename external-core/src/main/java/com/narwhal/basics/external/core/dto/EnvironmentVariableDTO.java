package com.narwhal.basics.external.core.dto;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.narwhal.basics.external.core.model.EnvironmentVariableUpdatable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Index
public class EnvironmentVariableDTO implements EnvironmentVariableUpdatable, Comparable<EnvironmentVariableDTO> {

    private String id;
    private int sort;
    private String value;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public int compareTo(EnvironmentVariableDTO o) {
        return new Integer(this.sort).compareTo(o.sort);
    }
}
