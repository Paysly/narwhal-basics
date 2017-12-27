package com.narwhal.basics.core.jobs.dto;


import com.narwhal.basics.core.jobs.model.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by tomyair on 8/22/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDetail implements Serializable {

    private JobStatus jobStatus;
    private JobDetailCurrentStatus currentStatuses;
}