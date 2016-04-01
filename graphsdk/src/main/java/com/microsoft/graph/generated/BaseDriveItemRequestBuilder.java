// ------------------------------------------------------------------------------
// Copyright (c) Microsoft Corporation.  All Rights Reserved.  Licensed under the MIT License.  See License in the project root for license information.
// ------------------------------------------------------------------------------

package com.microsoft.graph.generated;

import com.microsoft.graph.concurrency.*;
import com.microsoft.graph.core.*;
import com.microsoft.graph.extensions.*;
import com.microsoft.graph.http.*;
import com.microsoft.graph.generated.*;
import com.microsoft.graph.options.*;
import com.microsoft.graph.serializer.*;

import java.util.Arrays;
import java.util.List;

// **NOTE** This file was generated by a tool and any changes will be overwritten.

/**
 * The class for the Base Drive Item Request Builder.
 */
public class BaseDriveItemRequestBuilder extends BaseRequestBuilder implements IBaseDriveItemRequestBuilder {

    /**
     * The request builder for the DriveItem
     *
     * @param requestUrl The request url
     * @param client The service client
     * @param options The options for this request
     */
    public BaseDriveItemRequestBuilder(final String requestUrl, final IBaseClient client, final List<Option> options) {
        super(requestUrl, client, options);
    }

    /**
     * Creates the request
     */
    public IDriveItemRequest buildRequest() {
        return buildRequest(getOptions());
    }

    /**
     * Creates the request with specific options instead of the existing options
     */
    public IDriveItemRequest buildRequest(final List<Option> options) {
        return new DriveItemRequest(getRequestUrl(), getClient(), options);
    }


    /**
     * Gets the request builder for User.
     */
    public IUserWithReferenceRequestBuilder getCreatedByUser() {
        return new UserWithReferenceRequestBuilder(getRequestUrlWithAdditionalSegment("createdByUser"), getClient(), null);
    }

    /**
     * Gets the request builder for User.
     */
    public IUserWithReferenceRequestBuilder getLastModifiedByUser() {
        return new UserWithReferenceRequestBuilder(getRequestUrlWithAdditionalSegment("lastModifiedByUser"), getClient(), null);
    }
    public IPermissionCollectionRequestBuilder getPermissions() {
        return new PermissionCollectionRequestBuilder(getRequestUrlWithAdditionalSegment("permissions"), getClient(), null);
    }

    public IPermissionRequestBuilder getPermissions(final String id) {
        return new PermissionRequestBuilder(getRequestUrlWithAdditionalSegment("permissions") + "/" + id, getClient(), null);
    }
    public IDriveItemCollectionRequestBuilder getChildren() {
        return new DriveItemCollectionRequestBuilder(getRequestUrlWithAdditionalSegment("children"), getClient(), null);
    }

    public IDriveItemRequestBuilder getChildren(final String id) {
        return new DriveItemRequestBuilder(getRequestUrlWithAdditionalSegment("children") + "/" + id, getClient(), null);
    }
    public IThumbnailSetCollectionRequestBuilder getThumbnails() {
        return new ThumbnailSetCollectionRequestBuilder(getRequestUrlWithAdditionalSegment("thumbnails"), getClient(), null);
    }

    public IThumbnailSetRequestBuilder getThumbnails(final String id) {
        return new ThumbnailSetRequestBuilder(getRequestUrlWithAdditionalSegment("thumbnails") + "/" + id, getClient(), null);
    }

    public IDriveItemStreamRequestBuilder getContent() {
        return new DriveItemStreamRequestBuilder(getRequestUrlWithAdditionalSegment("content"), getClient(), null);
    }

    public IDriveItemCreateLinkRequestBuilder getCreateLink(final String type, final String scope) {
        return new DriveItemCreateLinkRequestBuilder(getRequestUrlWithAdditionalSegment("microsoft.graph.createLink"), getClient(), null, type, scope);
    }

    public IDriveItemCopyRequestBuilder getCopy(final String name, final ItemReference parentReference) {
        return new DriveItemCopyRequestBuilder(getRequestUrlWithAdditionalSegment("microsoft.graph.copy"), getClient(), null, name, parentReference);
    }

    public IDriveItemSearchCollectionRequestBuilder getSearch(final String q) {
        return new DriveItemSearchCollectionRequestBuilder(getRequestUrlWithAdditionalSegment("microsoft.graph.search"), getClient(), null, q);
    }

    public IDriveItemDeltaCollectionRequestBuilder getDelta() {
        return new DriveItemDeltaCollectionRequestBuilder(getRequestUrlWithAdditionalSegment("microsoft.graph.delta"), getClient(), null);
    }
}
