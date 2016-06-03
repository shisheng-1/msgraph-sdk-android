package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Test cases for {@see UrlConnection}
 */
public class UrlConnectionTests extends AndroidTestCase {

    public void testUrlConnection(){
        final ITestData data = new ITestData() {
            @Override
            public int getRequestCode() {
                return 200;
            }

            @Override
            public String getJsonResponse() {
                return "{ \"id\": \"zzz\" }";
            }

            @Override
            public Map<String, String> getHeaders() {
                final HashMap<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json");
                return map;
            }
        };
        IConnection connection = new MockConnection(data);
        OutputStream outputStream = null;
        InputStream inputStream = null;
        int responseCode = 0;
        String responseMessage = null;
        Boolean success = false;
        try{
            outputStream = connection.getOutputStream();
            inputStream = connection.getInputStream();
            responseCode = connection.getResponseCode();
            responseMessage = connection.getResponseMessage();
            connection.close();
            success = true;
        }catch (Exception ex){
            success = false;
        }
        assertTrue(success);
        assertNotNull(connection);
        assertEquals(1,connection.getHeaders().size());
        assertNotNull(outputStream);
        assertNotNull(inputStream);
        assertEquals(15,connection.getContentLength());
        assertEquals(200,responseCode);
        assertEquals("This is response message",responseMessage);
        assertEquals("GET",connection.getRequestMethod());
    }
}
