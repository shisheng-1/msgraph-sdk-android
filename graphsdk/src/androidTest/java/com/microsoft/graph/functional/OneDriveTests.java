package com.microsoft.graph.functional;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import com.microsoft.graph.concurrency.ChunkedUploadProvider;
import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.concurrency.IProgressCallback;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.extensions.DriveItem;
import com.microsoft.graph.extensions.DriveItemUploadableProperties;
import com.microsoft.graph.extensions.UploadSession;
import com.microsoft.graph.options.Option;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


//@Suppress
public class OneDriveTests extends InstrumentationTestCase {

    private TestBase testBase;

    public void setUp() {
        testBase = new TestBase();
    }

    public void testChunkedUpload() {
        UploadSession uploadSession = testBase.graphClient
                .getMe()
                .getDrive()
                .getItems("015U5LKCX2DOQL7EKJH5GLYWCKI63VVRPF")
                .getItemWithPath("metadata.xml")
                .getCreateUploadSession(new DriveItemUploadableProperties())
                .buildRequest()
                .post();
        int maxChunkSize = 320 * 1024;
        File imgPath = new File("metadata.xml");

        try {
            int length = this.getClass().getClassLoader().getResource("metadata.xml").openConnection().getContentLength();
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("metadata.xml");

            ChunkedUploadProvider provider = new ChunkedUploadProvider(uploadSession, testBase.graphClient, in, length, File.class);

            IProgressCallback<DriveItem> callback = new IProgressCallback<DriveItem>() {
                @Override
                public void progress(long current, long max) {

                }

                @Override
                public void success(DriveItem driveItem) {
                    assertNotNull(driveItem);
                }

                @Override
                public void failure(ClientException ex) {
                    assertEquals(0,1);
                }
            };

            provider.upload(null, callback, maxChunkSize);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static File getFileFromPath(Object obj, String fileName) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }
}