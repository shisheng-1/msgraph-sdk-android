package com.microsoft.graph.concurrency;

import android.os.SystemClock;
import android.test.AndroidTestCase;

import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.core.GraphErrorCodes;
import com.microsoft.graph.logger.MockLogger;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Test cases for {@see DefaultExecutors}
 */
public class DefaultExecutorsTests extends AndroidTestCase {

    private MockLogger mLogger;
    private DefaultExecutors defaultExecutors;
    private String callbackResult;
    private ClientException clientException;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mLogger = new MockLogger();
        defaultExecutors = new DefaultExecutors(mLogger);
    }

    public void testNotNull() {
        assertNotNull(mLogger);
        assertNotNull(defaultExecutors);
    }

    public void testPerformOnBackground() {
        String expectedLogMessage = "Starting background task, current active count: 0";
        final AtomicBoolean success = new AtomicBoolean(false);
        defaultExecutors.performOnBackground(new Runnable() {
            @Override
            public void run() {
                success.set(true);
            }
        });
        // sleep 1 second for background task
        SystemClock.sleep(1000);
        assertTrue(success.get());
        assertEquals(1,mLogger.getLogMessages().size());
        assertTrue(mLogger.isExist(expectedLogMessage));
    }

    public void testPerformOnForegroundWithResult() {
        String expectedResult = "result value";
        String expectedLogMessage = "Starting foreground task, current active count:0, with result result value";
        final AtomicBoolean success = new AtomicBoolean(false);
        final AtomicBoolean failure = new AtomicBoolean(false);
        ICallback<String> callback = new ICallback<String>() {
            @Override
            public void success(String s) {
                success.set(true);
                callbackResult = s;
            }

            @Override
            public void failure(ClientException ex) {
                failure.set(true);
            }
        };
        defaultExecutors.performOnForeground(expectedResult,callback);
        SystemClock.sleep(1000);
        assertTrue(success.get());
        assertFalse(failure.get());
        assertEquals(expectedResult, callbackResult);
        assertEquals(1,mLogger.getLogMessages().size());
        assertTrue(mLogger.isExist(expectedLogMessage));
    }

    public void testPerformOnForegroundWithProgress() {
        String expectedLogMessage = "Starting foreground task, current active count:0, with progress  1, max progress1";
        final AtomicBoolean success = new AtomicBoolean(false);
        final AtomicBoolean progress = new AtomicBoolean(false);
        final AtomicBoolean failure = new AtomicBoolean(false);
        IProgressCallback<String> callback = new IProgressCallback<String>() {
            @Override
            public void progress(long current, long max) {
                progress.set(true);
            }

            @Override
            public void success(String s) {
                success.set(true);
            }

            @Override
            public void failure(ClientException ex) {
                failure.set(true);
            }
        };

        defaultExecutors.performOnForeground(1,1,callback);
        SystemClock.sleep(1000);
        assertTrue(progress.get());
        assertFalse(success.get());
        assertFalse(failure.get());
        assertEquals(1,mLogger.getLogMessages().size());
        assertTrue(mLogger.isExist(expectedLogMessage));
    }

    public void testPerformOnForegroundWithClientException() {
        String expectedExceptionMessage = "client exception message";
        String expectedLogMessage = "Starting foreground task, current active count:0, with exception com.microsoft.graph.core.ClientException: client exception message";
        final AtomicBoolean success = new AtomicBoolean(false);
        final AtomicBoolean failure = new AtomicBoolean(false);
        ICallback<String> callback = new ICallback<String>() {
            @Override
            public void success(String s) {
                success.set(true);
            }

            @Override
            public void failure(ClientException ex) {
                failure.set(true);
                clientException = ex;
            }
        };

        defaultExecutors.performOnForeground(new ClientException(expectedExceptionMessage,null, GraphErrorCodes.InvalidAcceptType),callback);
        SystemClock.sleep(1000);
        assertFalse(success.get());
        assertTrue(failure.get());
        assertEquals(expectedExceptionMessage, clientException.getMessage());
        assertTrue(clientException.isError(GraphErrorCodes.InvalidAcceptType));
        assertEquals(1,mLogger.getLogMessages().size());
        assertTrue(mLogger.isExist(expectedLogMessage));
    }
}
