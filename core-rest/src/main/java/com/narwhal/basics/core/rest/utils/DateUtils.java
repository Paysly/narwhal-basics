package com.narwhal.basics.core.rest.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by tomyair on 8/19/17.
 */
public class DateUtils {

    public static Date increaseOneDay(Long time) {
        Date date = new Date();
        date.setTime(time);
        return increaseOneDay(date);
    }

    public static Date increaseOneDay(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Date decreaseOneDay(Long time) {
        Date date = new Date();
        date.setTime(time);
        return decreaseOneDay(date);
    }

    public static Date decreaseOneDay(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date dateWithNoTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date todayNoTime() {
        return dateWithNoTime(new Date());
    }

    public static Date yesterdayNoTime() {
        return dateWithNoTime(decreaseOneDay(new Date()));
    }

    public static Integer getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date moveWeeksBeforeToday(int weeks) {
        Date date = todayNoTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -weeks * 7);
        return cal.getTime();
    }

    public static Date getFirstDayOfWeek(Integer week) {
        Date date = todayNoTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    public static Date getDayAMonthAgo() {
        Date date = todayNoTime();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }
}
