package com.microsoft.graph.serializer;

import android.test.AndroidTestCase;

import com.microsoft.graph.model.DateOnly;

public class DateOnlySerializerTest extends AndroidTestCase {

    public void testDateSerializer() throws Exception {
        String strDate = DateOnlySerializer.serialize(new DateOnly("2016-04-27"));
        assertEquals("2016-04-27", strDate);
    }

    public void testDateSerializerIndefinite() throws Exception {
        String strDate = DateOnlySerializer.serialize(new DateOnly("0001-01-01"));
        assertEquals("0001-01-01", strDate);
    }

    public void testDateDeserializer() throws Exception {
        DateOnly date = DateOnlySerializer.deserialize("2016-04-27");
        assertEquals(2016, date.getYear());
        assertEquals(4, date.getMonth());
        assertEquals(27, date.getDay());
    }

    public void testDateDeserializerIndefinite() {
        DateOnly date = DateOnlySerializer.deserialize("0001-01-01");
        assertEquals(1, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(1, date.getDay());
    }
}
