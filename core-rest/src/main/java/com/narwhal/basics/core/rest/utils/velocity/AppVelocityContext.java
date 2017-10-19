package com.narwhal.basics.core.rest.utils.velocity;

import com.narwhal.basics.core.rest.utils.SharedConstants;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.generic.NumberTool;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Tomas de Priede
 */
public class AppVelocityContext extends VelocityContext {

    private static final String NUMBER_TOOL = "number";
    public static final String DATE_NUMBER = "DATE_NUMBER";
    public static final String DATE_MONTH = "DATE_MONTH";
    public static final String DATE_YEAR = "DATE_YEAR";

    public AppVelocityContext() {
        super();
        put(NUMBER_TOOL, new NumberTool());
        //
        GregorianCalendar calendar = new GregorianCalendar();
        put(DATE_NUMBER, calendar.get(Calendar.DATE));
        put(DATE_MONTH, SharedConstants.getCalendarMonthName(calendar.get(Calendar.MONTH)));
        put(DATE_YEAR, calendar.get(Calendar.YEAR));
    }

    @Override
    public Object put(String key, Object value) {
        /*
         * Return -- if the value is null
         */
        if (value == null) {
            value = "--";
        }
        return super.put(key, value);
    }
}