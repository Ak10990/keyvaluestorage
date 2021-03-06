package com.android.keyvaluestorage;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.android.keyvaluestorage.dbmodels.DataType;
import com.android.keyvaluestorage.dbmodels.KeyValueItem;
import com.android.keyvaluestorage.helper.DBHelper;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    private Context getTestContext() {
        Instrumentation instrumentation = new Instrumentation();
        InstrumentationRegistry.registerInstance(instrumentation, null);
        return InstrumentationRegistry.getContext();
    }

    @Test
    public void concurrency_isCorrect() throws Exception {
        DBHelper.init(getTestContext());
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    KeyValueItem keyValueItem = new KeyValueItem("key" + finalI, "value" + finalI, DataType.TYPE_STRING.ordinal());
                    keyValueItem.insertOrReplaceToDb();
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(50, TimeUnit.SECONDS);

    }

}