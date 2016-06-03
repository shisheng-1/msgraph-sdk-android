package com.microsoft.graph.http;

import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.serializer.ISerializer;

/**
 * Mock for {@see IHttpProvider}
 */
public class MockHttpProvider implements IHttpProvider {
    @Override
    public ISerializer getSerializer() {
        return null;
    }

    @Override
    public <Result, BodyType> void send(IHttpRequest request,
                                        ICallback<Result> callback,
                                        Class<Result> resultClass,
                                        BodyType serializable) {

    }

    @Override
    public <Result, BodyType> Result send(IHttpRequest request,
                                          Class<Result> resultClass,
                                          BodyType serializable) throws ClientException {
        return null;
    }
}
