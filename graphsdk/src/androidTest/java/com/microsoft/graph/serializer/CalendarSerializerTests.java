package com.microsoft.graph.serializer;

import android.test.AndroidTestCase;
import junit.framework.Assert;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * Test cases for {@see CalendarSerializer}
 */
public class CalendarSerializerTests extends AndroidTestCase {

    public void testCalendarSerialize() throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("PST"));
        final Calendar date = Calendar.getInstance();
        date.setTime(new Date(123456789012345L));
        Assert.assertEquals("5882-03-11T00:30:12.345Z", CalendarSerializer.serialize(date));

        final Calendar dateNoMillis = Calendar.getInstance();
        dateNoMillis.setTime(new Date(123456789012000L));
        Assert.assertEquals("5882-03-11T00:30:12.000Z", CalendarSerializer.serialize(dateNoMillis));
    }

    public void testCalendarDeserialize() throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("PST"));
        final long toTheSecondDate = 123456789012000L;
        final Calendar dateToSecond = CalendarSerializer.deserialize("5882-03-11T00:30:12Z");
        Assert.assertEquals(toTheSecondDate, dateToSecond.getTimeInMillis());

        final long toTheMillisecondDate = 123456789012345L;
        final Calendar dateToTheMillisecond = CalendarSerializer.deserialize("5882-03-11T00:30:12.345Z");
        Assert.assertEquals(toTheMillisecondDate, dateToTheMillisecond.getTimeInMillis());

        final Calendar dateToTheExtremeMillisecond = CalendarSerializer.deserialize("5882-03-11T00:30:12.3456789Z");
        Assert.assertEquals(toTheMillisecondDate, dateToTheExtremeMillisecond.getTimeInMillis());
    }
}
