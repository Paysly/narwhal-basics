package com.narwhal.basics.core.rest.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {

    @Test
    public void test_dateWithNoTime() {
        Date date = new Date();
        //
        Date result = DateUtils.dateWithNoTime(date);
        //
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date expected = cal.getTime();
        //
        assertEquals(expected, result);
    }

    @Test
    public void test_todayNoTime() {
        Date date = new Date();
        //
        Date result = DateUtils.todayNoTime();
        //
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date expected = cal.getTime();
        //
        assertEquals(expected, result);
    }

    @Test
    public void test_getWeekOfYear() {
        Date date = new Date();
        //
        int result = DateUtils.getWeekOfYear(date);
        //
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int expected = cal.get(Calendar.WEEK_OF_YEAR);
        //
        assertEquals(expected, result);
    }

    @Test
    public void test_moveWeeksBeforeToday() {
        Date date = DateUtils.todayNoTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -7);
        //
        Date result = DateUtils.moveWeeksBeforeToday(1);
        //
        Date expected = cal.getTime();
        //
        assertEquals(expected, result);
    }

    @Test
    public void test_increaseOneDay() {
        GregorianCalendar calendarIncreaseOneDay = new GregorianCalendar();
        calendarIncreaseOneDay.set(2010, 11, 13, 0, 0, 0);
        calendarIncreaseOneDay.set(Calendar.MILLISECOND, 0);
        //
        GregorianCalendar calendarDay = new GregorianCalendar();
        calendarDay.set(2010, 11, 12, 0, 0, 0);
        calendarDay.set(Calendar.MILLISECOND, 0);
        //
        Date dateExpected = calendarIncreaseOneDay.getTime();
        Date date = DateUtils.increaseOneDay(calendarDay.getTime().getTime());
        assertEquals(date, dateExpected);
    }

    @Test
    public void test_decreaseOneDay() {
        GregorianCalendar calendarDecreaseOneDay = new GregorianCalendar();
        calendarDecreaseOneDay.set(2010, 11, 11, 0, 0, 0);
        calendarDecreaseOneDay.set(Calendar.MILLISECOND, 0);
        //
        GregorianCalendar calendarDay = new GregorianCalendar();
        calendarDay.set(2010, 11, 12, 0, 0, 0);
        calendarDay.set(Calendar.MILLISECOND, 0);
        //
        Date dateExpected = calendarDecreaseOneDay.getTime();
        Date date = DateUtils.decreaseOneDay(calendarDay.getTime());
        assertEquals(date, dateExpected);
    }

    @Test
    public void test_getFirstDayOfWeek() {

        Integer week = 36;
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 8, 3, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date dateExpected = cal.getTime();
        Date date = DateUtils.getFirstDayOfWeek(week);
        assertEquals(dateExpected, date);
        //
        week = 37;
        cal = Calendar.getInstance();
        cal.set(2017, 8, 10, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dateExpected = cal.getTime();
        date = DateUtils.getFirstDayOfWeek(week);
        assertEquals(dateExpected, date);
    }
}
