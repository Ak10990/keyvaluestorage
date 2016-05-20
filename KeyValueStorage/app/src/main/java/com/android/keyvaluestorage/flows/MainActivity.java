package com.android.keyvaluestorage.flows;

import android.app.Activity;
import android.os.Bundle;

import com.android.keyvaluestorage.helper.StorageHelper;
import com.android.keyvaluestorage.utils.Logger;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StorageHelper storageHelper = new StorageHelper();
        storageHelper.clear();
        storageHelper.putKeyValue(loadJSONFromAsset());

        Logger.d("List is " + storageHelper.listAll());
    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("sample.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}