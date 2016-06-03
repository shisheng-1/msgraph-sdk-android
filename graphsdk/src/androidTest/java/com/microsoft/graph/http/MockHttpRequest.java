package com.microsoft.graph.http;

import com.microsoft.graph.options.HeaderOption;
import com.microsoft.graph.options.Option;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock for {@see IHttpRequest}
 */
public class MockHttpRequest implements IHttpRequest {
    @Override
    public URL getRequestUrl() {
        try {
            return new URL("http://localhost");
        } catch (final MalformedURLException ignored) {
        }
        return null;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public List<HeaderOption> getHeaders() {
        return new ArrayList<>();
    }

    @Override
    public List<Option> getOptions() {
        return new ArrayList<>();
    }

    @Override
    public void addHeader(String header, String value) {

    }
}
