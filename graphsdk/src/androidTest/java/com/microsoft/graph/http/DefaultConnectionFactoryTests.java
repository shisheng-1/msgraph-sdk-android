package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import java.io.IOException;

/**
 * Test cases for {@see DefaultConnectionFactory}
 */
public class DefaultConnectionFactoryTests extends AndroidTestCase {

    public void testCreateFromRequest() {
        Boolean success = false;
        Boolean failure = false;
        IConnection connection = null;
        DefaultConnectionFactory defaultConnectionFactory = new DefaultConnectionFactory();
        try{
            connection = defaultConnectionFactory.createFromRequest(new MockHttpRequest());
            success = true;
        }catch (IOException ex){
            failure = true;
        }

        assertTrue(success);
        assertFalse(failure);
        assertNotNull(connection);
        assertEquals("GET", connection.getRequestMethod());
    }

}
