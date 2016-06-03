package com.microsoft.graph.serializer;

import android.test.AndroidTestCase;

/**
 * Test cases for {@see ByteArraySerializer}
 */
public class ByteArraySerializerTests extends AndroidTestCase {

    public void testByteArraySerializer() throws Exception {
        byte[] expectedBytes = new byte[]{1,2,3,4};
        String expectedString = "abcd";
        String serializeString = ByteArraySerializer.serialize(expectedBytes);
        byte[] deserializeBytes = ByteArraySerializer.deserialize(serializeString);
        byte[] deserializeBytes2 = ByteArraySerializer.deserialize(expectedString);
        String serializeString2 = ByteArraySerializer.serialize(deserializeBytes2);
        assertEquals(expectedBytes.length, deserializeBytes.length);
        assertEquals(expectedString, serializeString2);
        assertFalse(ByteArraySerializer.serialize(new byte[]{1,2,3}).equals(ByteArraySerializer.serialize(new byte[]{1,2,3,4})));
    }
}
