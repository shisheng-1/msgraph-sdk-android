package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import com.google.gson.JsonObject;
import com.microsoft.graph.core.IBaseClient;
import com.microsoft.graph.core.MockBaseClient;
import com.microsoft.graph.serializer.ISerializer;
import com.microsoft.graph.serializer.MockSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Test cases for {@see BaseCollectionPage}
 */
public class BaseCollectionPageTests extends AndroidTestCase {

    private IRequestBuilder mRequestBuilder;
    private static ArrayList<String> list;
    private BaseCollectionPage baseCollectionPage;
    private String requestUrl = "https://graph.microsoft.com/v1.0/me";

    @Override
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception {
        super.setUp();
        list = new ArrayList<String>();
        list.add("Object1");
        list.add("Object2");
        list.add("Object3");
        IBaseClient mBaseClient = new MockBaseClient();
        mRequestBuilder = new MockRequestBuilder(mBaseClient,requestUrl);
        baseCollectionPage = new BaseCollectionPage(list, mRequestBuilder) {
            @Override
            public IRequestBuilder getNextPage() {
                return super.getNextPage();
            }

            @Override
            public List getCurrentPage() {
                return super.getCurrentPage();
            }

            @Override
            public JsonObject getRawObject() {
                return super.getRawObject();
            }

            @Override
            protected ISerializer getSerializer() {
                return super.getSerializer();
            }

            @Override
            public void setRawObject(ISerializer serializer, JsonObject json) {
                super.setRawObject(serializer, json);
            }
        };
    }

    public void testNotNull() {
        assertNotNull(baseCollectionPage);
    }

    public void testCurrentPage() {
        assertEquals(3,baseCollectionPage.getCurrentPage().size());
        assertEquals("Object2", baseCollectionPage.getCurrentPage().get(1));
    }

    public void testNextPage() {
        assertEquals(requestUrl, baseCollectionPage.getNextPage().getRequestUrl());
        assertNotNull(baseCollectionPage.getNextPage().getClient());
        assertEquals("https://graph.microsoft.com/v1.0", baseCollectionPage.getNextPage().getClient().getServiceRoot());
    }

    public void testRawObject() {
        String expectedKey = "keyString";
        String expectedValue = "valueString";
        ISerializer serializer = new MockSerializer(null, null);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(expectedKey, expectedValue);
        baseCollectionPage.setRawObject(serializer,jsonObject);
        assertNotNull(baseCollectionPage.getRawObject());
        assertNotNull(baseCollectionPage.getSerializer());
        assertEquals(expectedValue, baseCollectionPage.getRawObject().get(expectedKey).getAsString());
    }
}
