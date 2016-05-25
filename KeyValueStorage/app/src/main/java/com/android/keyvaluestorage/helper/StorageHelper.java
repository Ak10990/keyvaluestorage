package com.android.keyvaluestorage.helper;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by akanksha on 19/5/16.
 */
public class StorageHelper {

    private KeyValueHelper keyValueHelper;

    public StorageHelper() {
        keyValueHelper = new KeyValueHelper();
    }

    public void putKeyValue(String key, Object value) {
        if (value == null)
            return;
        if (value instanceof String) {
            keyValueHelper.putKeyValue(key, (String) value);
        } else if (value instanceof Boolean) {
            keyValueHelper.putKeyValue(key, (boolean) value);
        } else if (value instanceof Integer) {
            keyValueHelper.putKeyValue(key, (int) value);
        } else if (value instanceof Short) {
            keyValueHelper.putKeyValue(key, (short) value);
        } else if (value instanceof Long) {
            keyValueHelper.putKeyValue(key, (long) value);
        } else if (value instanceof Byte) {
            keyValueHelper.putKeyValue(key, (byte) value);
        } else if (value instanceof Float) {
            keyValueHelper.putKeyValue(key, (float) value);
        } else if (value instanceof Double) {
            keyValueHelper.putKeyValue(key, (double) value);
        } else if (value instanceof Character) {
            keyValueHelper.putKeyValue(key, (char) value);
        } else if (value instanceof Date) {
            keyValueHelper.putKeyValue(key, (Date) value);
        } else if (value instanceof JSONObject) {
            keyValueHelper.putKeyValue(key, (JSONObject) value);
        } else {
            keyValueHelper.putKeyValue(key, value);
        }
    }

    public Object getKeyValue(String key) {
        return keyValueHelper.getKeyValue(key);
    }

    public void clear() {
        keyValueHelper.clear();
    }

    public String listAll() {
        return keyValueHelper.listAll().toString();
    }

    public long countAll() {
        return keyValueHelper.getCount();
    }

    public void delete(String key) {
        keyValueHelper.delete(key);
    }

}
