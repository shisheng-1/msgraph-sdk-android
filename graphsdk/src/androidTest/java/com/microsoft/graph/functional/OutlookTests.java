package com.microsoft.graph.functional;

import android.test.AndroidTestCase;

//import com.microsoft.graph.extensions.IDirectoryDeletedItemsCollectionPage;
import com.microsoft.graph.extensions.IDirectoryObjectCollectionPage;
import com.microsoft.graph.extensions.IDriveItemCollectionPage;
import com.microsoft.graph.extensions.IDriveItemCollectionRequestBuilder;
import com.microsoft.graph.extensions.IGraphServiceClient;

public class OutlookTests extends AndroidTestCase {

    public void testQueryDeletedItems() {
        TestBase testBase = new TestBase();
        //IDirectoryDeletedItemsCollectionPage deletedItems = testBase.graphClient.getDirectory().getDeletedItemsByType("microsoft.graph.group").buildRequest().get();
        //assertNotNull(deletedItems);
    }
}