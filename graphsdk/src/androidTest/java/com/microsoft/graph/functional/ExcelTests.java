package com.microsoft.graph.functional;

import android.test.AndroidTestCase;

//import com.microsoft.graph.extensions.IDirectoryDeletedItemsCollectionPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.microsoft.graph.extensions.DriveItem;
import com.microsoft.graph.extensions.File;
import com.microsoft.graph.extensions.IDirectoryObjectCollectionPage;
import com.microsoft.graph.extensions.IDriveItemCollectionPage;
import com.microsoft.graph.extensions.IDriveItemCollectionRequestBuilder;
import com.microsoft.graph.extensions.IGraphServiceClient;
import com.microsoft.graph.extensions.IWorkbookNamedItemCollectionPage;
import com.microsoft.graph.extensions.Workbook;
import com.microsoft.graph.extensions.WorkbookNamedItem;
import com.microsoft.graph.extensions.WorkbookNamedItemCollectionPage;
import com.microsoft.graph.extensions.WorkbookRange;
import com.microsoft.graph.http.GraphServiceException;


public class ExcelTests extends AndroidTestCase {

    private TestBase testBase;
    private String fileId;

    public void setUp()
    {
        testBase = new TestBase();
    }

    public void testCreateNamedItem() {
        JsonObject range = new JsonObject();
        range.addProperty("address", "Sheet1!A1");
        WorkbookNamedItem item = testBase.graphClient
                .getMe()
                .getDrive()
                .getItems("015U5LKCVICVSIQ6DKOVELGXD3WAEHMMAI")
                .getWorkbook()
                .getNames()
                .getAdd("testNamedRange", new JsonPrimitive("Sheet1!A1"), "Comment")
                .buildRequest()
                .post();
        assertNotNull(item);
        assertEquals("testNamedRange", item.name);
    }

    public void testGetNamedItems() {
//        JsonObject range = new JsonObject();
//        range.addProperty("range", "A1");
//        WorkbookNamedItem item = testBase.graphClient.getMe().getDrive().getItems(fileId).getWorkbook().getNames().getAdd("named-range", range, "Comment").buildRequest().post();
//
//        IWorkbookNamedItemCollectionPage namedItems = testBase.graphClient.getMe().getDrive().getItems(fileId).getWorkbook().getNames().buildRequest().get();
//        assertNotNull(namedItems);
    }
}