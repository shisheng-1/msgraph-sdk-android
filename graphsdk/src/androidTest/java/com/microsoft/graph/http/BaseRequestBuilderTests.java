package com.microsoft.graph.http;

import android.test.AndroidTestCase;

/**
 * Test cases for {@see BaseRequestBuilder}
 */
public class BaseRequestBuilderTests extends AndroidTestCase {

    private String expectedRequestUrl = "https://a.b.c";

    public void testBaseRequestBuilder() {
        BaseRequestBuilder baseRequestBuilder = new BaseRequestBuilder(expectedRequestUrl,null,null){};
        assertEquals(expectedRequestUrl, baseRequestBuilder.getRequestUrl());
        assertEquals(0, baseRequestBuilder.getOptions().size());
        assertEquals(expectedRequestUrl+"/me", baseRequestBuilder.getRequestUrlWithAdditionalSegment("me"));
        assertEquals(expectedRequestUrl + "('version=1.0')", baseRequestBuilder.getRequestUrlWithAdditionalParameter("version=1.0"));
    }
}
