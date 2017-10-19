package com.narwhal.basics.core.rest.utils.jsoap;

import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;

public class JsoapUtils {

    public static String getHtmlNoEmpty(Document html) {
        return getHtmlNoEmpty(html.body().outerHtml());
    }

    public static String getHtmlNoEmpty(String html) {
        ApiPreconditions.checkNotNull(html, "html");
        String outputHtml = StringUtils.replace(html, "<body>", "");
        outputHtml = StringUtils.replace(outputHtml, "</body>", "");
        outputHtml = StringUtils.replace(outputHtml, "\n", "");
        outputHtml = StringUtils.replace(outputHtml, "<p></p>", "");
        outputHtml = StringUtils.replace(outputHtml, "<p> </p>", "");
        //
        return outputHtml;
    }
}
