package com.narwhal.basics.core.rest.utils.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas de Priede
 */
public class ListsUtils {

    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> List<T> newArrayList(T... ts) {
        List<T> list = newArrayList();
        for (T t : ts) {
            list.add(t);
        }
        return list;
    }

    public static <T> List<T> newArrayListIgnoreNulls(T... ts) {
        List<T> list = newArrayList();
        for (T t : ts) {
            if (t instanceof ArrayList) {
                for (T value : ((ArrayList<T>) t)) {
                    if (value != null) {
                        list.add(value);
                    }
                }
            } else if (t != null) {
                list.add(t);
            }
        }
        return list;
    }
}
