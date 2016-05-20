package com.android.keyvaluestorage.helper;

import com.android.keyvaluestorage.dbmodels.DataType;
import com.android.keyvaluestorage.dbmodels.KeyValueItem;
import com.android.keyvaluestorage.utils.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by akanksha on 19/5/16.
 */
public class StorageHelper {

    private KeyValueHelper keyValueHelper;

    public StorageHelper() {
        keyValueHelper = new KeyValueHelper();
    }

    public void putKeyValue(Object json) {

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
        } /*else if (value instanceof JSONObject) {
            keyValueHelper.putKeyValue(key, (JSONObject) value);
        } else if (value instanceof JSONArray) {
            Logger.w("Trying to          <" + key + " ,   JSONArray       ");
            configureJsonArray(value.toString());
        } else{}*/
    }

    /*
    private void configureJsonArray(String value) {
        try {
            JSONArray jsonArray = new JSONArray(value);
            *//*Iterator<String> iterator = jsonObject.keys();
            String newkey;
            while (iterator.hasNext()) {
                newkey = iterator.next();
                Object newValue = jsonObject.get(newkey);
//                Logger.w("Trying to key        " + newkey);
                putKeyValue(newkey, newValue);
            }*//*

            Logger.d("Trying to array       " + jsonArray.get(0));
//            putKeyValue(newkey, newValue);

        } catch (JSONException e) {
            e.printStackTrace();
            Logger.w("Trying to          <" + " ,  String ");
        }
    }

    private void configureJsonObject(String value) {
        try {
            JSONObject jsonObject = new JSONObject(value);
            Iterator<String> iterator = jsonObject.keys();
            String newkey;
            while (iterator.hasNext()) {
                newkey = iterator.next();
                Object newValue = jsonObject.get(newkey);
//                Logger.w("Trying to key        " + newkey);
                putKeyValue(newkey, newValue);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Logger.w("Trying to          <" + " ,  String ");
        }
    }
*/

    public void clear() {
        KeyValueItem.deleteAllFromDatabase();
    }

    public List<KeyValueItem> listAll() {
        return KeyValueItem.listAll();
    }

    public List<KeyValueItem> list(Class<?> clazz) {
        int type = getType(clazz);
        if (type != -1) {
            return KeyValueItem.listAll(type);
        }
        return null;
    }

    public void delete(Class<?> clazz) {
        int type = getType(clazz);
        if (type != -1) {
            KeyValueItem.deleteAllFromDatabase(type);
        }
    }

    private int getType(Class<?> clazz) {
        int type = -1;
        if (clazz.equals(String.class)) {
            type = DataType.TYPE_STRING.ordinal();
        } else if (clazz.equals(Boolean.class)) {
            type = DataType.TYPE_BOOLEAN.ordinal();
        } else if (clazz.equals(Integer.class)) {
            type = DataType.TYPE_INT.ordinal();
        } else if (clazz.equals(Long.class)) {
            type = DataType.TYPE_LONG.ordinal();
        } else if (clazz.equals(Float.class)) {
            type = DataType.TYPE_FLOAT.ordinal();
        } else if (clazz.equals(Date.class)) {
            type = DataType.TYPE_DATE.ordinal();
        } else if (clazz.equals(ArrayList.class)) {
            type = DataType.TYPE_STRINGLIST.ordinal();//TOdO Check string
        } else {
            Logger.w("This class is not supported yet.");
        }
        return type;
    }

}
