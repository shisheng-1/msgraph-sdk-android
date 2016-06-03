package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import java.util.ArrayList;

/**
 * Test cases for {@see GraphFatalServiceException}
 */
public class GraphFatalServiceExceptionTests extends AndroidTestCase {

    public void testGraphFatalServiceException() {
        GraphFatalServiceException exception = new GraphFatalServiceException(null,null,new ArrayList<String>(),null,401,"Unauthenticated",new ArrayList<String>(),null);
        String message = exception.getMessage();
        assertTrue(message.indexOf("This is an unexpected error from Graph") == 1);
        assertTrue(message.indexOf("401 : Unauthenticated") > 0);
    }
}
