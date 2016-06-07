package com.microsoft.graph.concurrency;

import android.os.Looper;
import android.os.SystemClock;
import android.test.AndroidTestCase;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Test cases for {@see SynchronousExecutor}
 */
public class SynchronousExecutorTests extends AndroidTestCase {

    public void testExecute() {
        final AtomicBoolean success = new AtomicBoolean(false);
        SynchronousExecutor synchronousExecutor = new SynchronousExecutor();
        synchronousExecutor.execute(new Runnable() {
            @Override
            public void run() {
                success.set(true);
            }
        });
        SystemClock.sleep(1000);
        new SimpleWaiter().signal();
        assertFalse(Looper.getMainLooper().isCurrentThread());
        assertNotNull(synchronousExecutor);
        assertTrue(success.get());
        assertEquals(0, synchronousExecutor.getActiveCount());
    }
}
