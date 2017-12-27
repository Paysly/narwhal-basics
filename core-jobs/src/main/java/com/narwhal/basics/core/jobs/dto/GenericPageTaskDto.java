package com.narwhal.basics.core.jobs.dto;


import com.narwhal.basics.core.jobs.model.HasJobId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by tomyair on 8/18/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericPageTaskDto implements Serializable, HasJobId {

    private String jobId;
    private String clazzName;
    private String cursor;
}
