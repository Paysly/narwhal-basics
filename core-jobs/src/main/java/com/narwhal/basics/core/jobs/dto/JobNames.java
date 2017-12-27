package com.narwhal.basics.core.jobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomyair on 8/22/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobNames implements Serializable {

    private List<String> names = new ArrayList<>();
}
