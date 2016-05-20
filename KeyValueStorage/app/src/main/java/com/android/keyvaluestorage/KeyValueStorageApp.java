package com.android.keyvaluestorage;

import android.app.Application;

import com.android.keyvaluestorage.helper.DBHelper;
import com.android.keyvaluestorage.utils.Logger;

public class KeyValueStorageApp extends Application {

    public static KeyValueStorageApp mInstance;

    public static KeyValueStorageApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initLogger();
        initStorageDb();
    }

    private void initLogger() {
        Logger.enableLogging(BuildConfig.DEBUG);
    }

    private void initStorageDb() {
        DBHelper.init(this);
    }
}
