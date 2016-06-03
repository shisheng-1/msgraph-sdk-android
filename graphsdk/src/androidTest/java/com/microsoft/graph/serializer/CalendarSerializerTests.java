package com.microsoft.graph.serializer;

import android.test.AndroidTestCase;

import java.util.Calendar;


/**
 * Test cases for {@see CalendarSerializer}
 */
public class CalendarSerializerTests extends AndroidTestCase {

    public void testCalendarSerializer() throws Exception {
        Calendar calendar = Calendar.getInstance();
        String calendarString = CalendarSerializer.serialize(calendar);
        Calendar deserializeCalendar = CalendarSerializer.deserialize(calendarString);
        assertEquals(calendar,deserializeCalendar);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH,1);
        String calendarStrings = CalendarSerializer.serialize(calendar1);
        assertFalse(calendarString.equals(calendarStrings));
    }
}
