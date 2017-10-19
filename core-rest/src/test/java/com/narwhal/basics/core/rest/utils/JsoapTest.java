package com.narwhal.basics.core.rest.utils;

import com.narwhal.basics.core.rest.utils.jsoap.AppWhitelist;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class JsoapTest {
    @Test
    public void test_sanitizeStringWithDataImage() {
        String unsafe = "<img src=\"data:image/jpeg;base64,/9j/4AAQSk\">";
        String safe = Jsoup.clean(unsafe, new AppWhitelist());
        //
        assertEquals(unsafe, safe);
    }

    @Test
    public void test_getStringWithDataImage() {
        String unsafe = "<p><p>hellow</p><img src=\"data:image/jpeg;base64,/9j/4AAQSk\"></p>";
        String safe = Jsoup.clean(unsafe, new AppWhitelist());
        //
        Document html = Jsoup.parse(safe);
        //
        for (Element e : html.select("img")) {
            String imgSrc = e.attr("src");
            assertEquals("data:image/jpeg;base64,/9j/4AAQSk", imgSrc);
            //
            // Replace string src by a http url
            e.attr("src", "http://www.google.com/img");
            //
        }
        //
        assertEquals("<body>\n" +
                " <p></p> \n" +
                " <p>hellow</p> \n" +
                " <img src=\"http://www.google.com/img\"> \n" +
                " <p></p>\n" +
                "</body>", html.body().outerHtml());
    }
}
