// ------------------------------------------------------------------------------
// Copyright (c) Microsoft Corporation.  All Rights Reserved.  Licensed under the MIT License.  See License in the project root for license information.
// ------------------------------------------------------------------------------

package com.microsoft.graph.core;

import com.microsoft.graph.http.BaseRequestBuilder;
import com.microsoft.graph.options.FunctionOption;
import com.microsoft.graph.options.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * The base method request builder class.
 */
public class BaseGetMethodRequestBuilder extends BaseRequestBuilder {

    /**
     * The {@link FunctionOption}s to add to this request
     */
    protected List<FunctionOption> mFunctionOptions = new ArrayList<>();

    /**
     * Constructs a new {@link BaseGetMethodRequestBuilder}
     *
     * @param requestUrl the URL for the request
     * @param client     the {@link IBaseClient} for handling requests
     * @param options    {@link List} of {@link Option}s to add to this request
     */
    public BaseGetMethodRequestBuilder(
            String requestUrl,
            IBaseClient client,
            List<Option> options
    ) {
        super(requestUrl, client, options);
    }

}
