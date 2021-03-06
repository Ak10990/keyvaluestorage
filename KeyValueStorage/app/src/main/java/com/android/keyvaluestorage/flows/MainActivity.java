package com.android.keyvaluestorage.flows;

import android.app.Activity;
import android.os.Bundle;

import com.android.keyvaluestorage.helper.StorageHelper;
import com.android.keyvaluestorage.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allModules();
    }

    private void allModules() {
        StorageHelper storageHelper = new StorageHelper();
        storageHelper.clear();
        storageHelper.putKeyValue("comandroidkey", loadJSONFromAsset());
        Logger.printKeyValue("comandroidkey", storageHelper.getKeyValue("comandroidkey") + "");
        Logger.d("List is " + storageHelper.listAll());
        Logger.d("Count is " + storageHelper.countAll());
        storageHelper.delete("comandroidkey");
    }

    public JSONObject loadJSONFromAsset() {
        try {
            InputStream is = getAssets().open("sample.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            return new JSONObject(json);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}