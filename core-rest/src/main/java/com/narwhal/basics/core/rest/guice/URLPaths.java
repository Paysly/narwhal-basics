package com.narwhal.basics.core.rest.guice;

import com.google.common.base.Preconditions;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class URLPaths<T extends HttpServlet> {

    private final Logger logger = Logger.getLogger(URLPaths.class.getSimpleName());
    private final HashMap<String, Class<T>> paths = new HashMap<String, Class<T>>();

    public static <T extends Class<? extends Object>> String findTerminatedPath(T clazz) {
        Preconditions.checkNotNull(clazz);
        //
        String className = clazz.getCanonicalName();
        String basePathValue = null;
        String relativePathValue = null;
        BasePath basePath = clazz.getAnnotation(BasePath.class);
        if (basePath != null) {
            basePathValue = basePath.value();
        } else {
            // check for Cron
            Cron cronPath = clazz.getAnnotation(Cron.class);
            if (cronPath != null) {
                basePathValue = "/cron";
            } else {
                Task taskPath = clazz.getAnnotation(Task.class);
                if (taskPath != null) {
                    basePathValue = "/tasks";
                }
            }
        }
        //
        RelativePath relativePath = clazz.getAnnotation(RelativePath.class);
        if (relativePath != null) {
            relativePathValue = relativePath.value();
        }
        //
        checkNotNull(basePathValue, new StringBuilder("No base path on ").append(className).toString());
        checkNotNull(relativePathValue, new StringBuilder("Not relative path on ").append(className).toString());
        //
        StringBuilder path = new StringBuilder(basePathValue).append("/").append(relativePathValue);
        //
        String terminatedPath = normalizeSlashes(path.toString());
        return terminatedPath;
    }

    protected static String normalizeSlashes(String input) {
        Preconditions.checkNotNull(input);
        return input.replaceAll("//", "/");
    }
}