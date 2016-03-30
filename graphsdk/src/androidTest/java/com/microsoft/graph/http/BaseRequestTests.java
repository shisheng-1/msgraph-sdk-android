package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import com.microsoft.graph.options.FunctionOption;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;

import java.util.Arrays;

/**
 * Test cases for {@see BaseRequest}
 */
public class BaseRequestTests extends AndroidTestCase {
    public void testFunctionParameters() {
        final Option fo1 = new FunctionOption("1", "one");
        final Option fo2 = new FunctionOption("2", null);
        final IHttpRequest request = new BaseRequest("https://a.b.c", null, Arrays.asList(fo1, fo2), null){};
        assertEquals("https://a.b.c(1='one',2=null)", request.getRequestUrl().toString());
    }

    public void testFunctionQueryMixedParameters() {
        final Option fo1 = new FunctionOption("f1", "function");
        final Option qo1 = new QueryOption("q1", "option ");
        final Option qo2 = new QueryOption("q2", "second");
        final IHttpRequest request = new BaseRequest("https://a.b.c", null, Arrays.asList(fo1, qo1, qo2), null){};
        assertEquals("https://a.b.c(f1='function')?q1=option%20&q2=second", request.getRequestUrl().toString());
    }

}