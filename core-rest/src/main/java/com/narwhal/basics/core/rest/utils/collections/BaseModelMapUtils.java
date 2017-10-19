package com.narwhal.basics.core.rest.utils.collections;


import com.narwhal.basics.core.rest.model.BaseModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseModelMapUtils {

    public static <T extends BaseModel> Map<String, T> buildMapFromList(List<T> list) {
        Map<String, T> map = new HashMap<>();
        //
        for (T t : list) {
            map.put(t.getId(), t);
        }
        return map;
    }
}
