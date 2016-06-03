package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import com.microsoft.graph.authentication.MockAuthenticationProvider;
import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.concurrency.MockExecutors;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.core.MockBaseClient;
import com.microsoft.graph.logger.MockLogger;
import com.microsoft.graph.options.FunctionOption;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;
import com.microsoft.graph.serializer.MockSerializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Test cases for {@see BaseRequest}
 */
public class BaseRequestTests extends AndroidTestCase {
    private MockAuthenticationProvider mAuthenticationProvider;
    private MockBaseClient mBaseClient;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAuthenticationProvider = new MockAuthenticationProvider();
        mBaseClient = new MockBaseClient();
    }

    public void testSend() {
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
                map.put("Content-Type", "application/octet-stream");
                return map;
            }
        };
        DefaultHttpProvider mProvider = new DefaultHttpProvider(new MockSerializer(null, ""),
                mAuthenticationProvider,
                new MockExecutors(),
                new MockLogger());
        mProvider.setConnectionFactory(new MockConnectionFactory(new MockConnection(data)));
        mBaseClient.setHttpProvider(mProvider);
        final BaseRequest request = new BaseRequest("https://a.b.c", mBaseClient, null,null){};
        request.send(HttpMethod.GET, null);
        assertEquals(1, mAuthenticationProvider.getInterceptionCount());
    }

    public void testSendWithCallback() {
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
                map.put("Content-Type", "application/octet-stream");
                return map;
            }
        };
        DefaultHttpProvider mProvider = new DefaultHttpProvider(new MockSerializer(null, ""),
                mAuthenticationProvider,
                new MockExecutors(),
                new MockLogger());
        mProvider.setConnectionFactory(new MockConnectionFactory(new MockConnection(data)));
        mBaseClient.setHttpProvider(mProvider);
        final AtomicBoolean success = new AtomicBoolean(false);
        final AtomicBoolean failure = new AtomicBoolean(false);
        ICallback callback = new ICallback() {
            @Override
            public void success(Object o) {
                success.set(true);
            }

            @Override
            public void failure(ClientException ex) {
                failure.set(true);
            }
        };
        final BaseRequest request = new BaseRequest("https://a.b.c", mBaseClient, null,null){};
        request.send(HttpMethod.GET, callback,null);
        assertTrue(success.get());
        assertFalse(failure.get());
        assertEquals(1, mAuthenticationProvider.getInterceptionCount());
    }

    public void testFunctionParameters() {
        final Option fo1 = new FunctionOption("1", "one");
        final Option fo2 = new FunctionOption("2", null);
        final BaseRequest request = new BaseRequest("https://a.b.c", null, Arrays.asList(fo1, fo2), null){};
        assertEquals("https://a.b.c(1='one',2=null)", request.getRequestUrl().toString());
        request.addFunctionOption(new FunctionOption("3","two"));;
        assertEquals("https://a.b.c(1='one',2=null,3='two')", request.getRequestUrl().toString());
        assertEquals(4, request.getOptions().size());
    }

    public void testQueryParameters() {
        final Option q1 = new QueryOption("q1","option1 ");
        final Option q2 = new QueryOption("q2","option2");
        final BaseRequest request = new BaseRequest("https://a.b.c", null, Arrays.asList(q1, q2), null){};
        assertEquals("https://a.b.c?q1=option1%20&q2=option2", request.getRequestUrl().toString());
        request.addQueryOption(new QueryOption("q3","option3"));
        assertEquals("https://a.b.c?q1=option1%20&q2=option2&q3=option3", request.getRequestUrl().toString());
        assertEquals(4,request.getOptions().size());
    }

    public void testFunctionAndQueryParameters() {
        final Option f1 = new FunctionOption("f1", "fun1");
        final Option f2 = new FunctionOption("f2", null);
        final Option q1 = new QueryOption("q1","option1 ");
        final Option q2 = new QueryOption("q2","option2");
        final BaseRequest request = new BaseRequest("https://a.b.c", null, Arrays.asList(f1, f2, q1, q2), null){};
        assertEquals("https://a.b.c(f1='fun1',f2=null)?q1=option1%20&q2=option2", request.getRequestUrl().toString());
        assertEquals(5, request.getOptions().size());
    }

    public void testHttpMethod() {
        final BaseRequest request = new BaseRequest("https://a.b.c", null, null, null){};
        assertNull(request.getHttpMethod());
        request.setHttpMethod(HttpMethod.GET);
        assertEquals(HttpMethod.GET, request.getHttpMethod());
    }

    public void testHeader() {
        String expectedHeader = "header key";
        String expectedValue = "header value";
        final BaseRequest request = new BaseRequest("https://a.b.c", null, null, null){};
        assertEquals(1, request.getHeaders().size());
        request.addHeader(expectedHeader,expectedValue);
        assertEquals(2,request.getHeaders().size());
    }
}