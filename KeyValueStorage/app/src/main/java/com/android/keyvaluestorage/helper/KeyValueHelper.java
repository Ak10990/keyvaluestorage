package com.android.keyvaluestorage.helper;

import android.text.TextUtils;

import com.android.keyvaluestorage.dbmodels.DataType;
import com.android.keyvaluestorage.dbmodels.KeyValueItem;
import com.android.keyvaluestorage.utils.DateUtils;
import com.android.keyvaluestorage.utils.Logger;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class KeyValueHelper {

    /**
     * Put and get
     */
    /**
     * String
     */
    public void putKeyValue(String key, String value) {
        putString(key, value);
    }

    public void putString(String key, String value) {
        KeyValueItem keyValueItem = new KeyValueItem(key, value, DataType.TYPE_STRING.ordinal());
        keyValueItem.insertOrReplaceToDb();
    }

    public String getString(String key, String defValue) {
        int type = DataType.TYPE_STRING.ordinal();
        List<KeyValueItem> list = KeyValueItem.listAll(key, type);
        if (list != null && list.size() > 0) {
            return list.get(0).getValue();
        }
        return defValue;
    }

    public String getKeyValue(String key, String defValue) {
        return getString(key, defValue);
    }

    /**
     * Boolean
     */
    public void putKeyValue(String key, boolean value) {
        putBoolean(key, value);
    }

    public void putBoolean(String key, Boolean value) {
        String valueString = value.toString();
        KeyValueItem keyValueItem = new KeyValueItem(key, valueString, DataType.TYPE_BOOLEAN.ordinal());
        keyValueItem.insertOrReplaceToDb();
    }

    public Boolean getBoolean(String key, boolean defValue) {
        int type = DataType.TYPE_BOOLEAN.ordinal();
        List<KeyValueItem> list = KeyValueItem.listAll(key, type);
        if (list != null && list.size() > 0) {
            return Boolean.valueOf(list.get(0).getValue());
        }
        return defValue;
    }

    public Boolean getKeyValue(String key, boolean defValue) {
        return getBoolean(key, defValue);
    }

    /**
     * Integer
     */
    public void putKeyValue(String key, int value) {
        putInt(key, value);
    }

    public void putInt(String key, Integer value) {
        String valueString = value.toString();
        KeyValueItem keyValueItem = new KeyValueItem(key, valueString, DataType.TYPE_INT.ordinal());
        keyValueItem.insertOrReplaceToDb();
    }

    public Integer getInt(String key, int defValue) {
        int type = DataType.TYPE_INT.ordinal();
        List<KeyValueItem> list = KeyValueItem.listAll(key, type);
        if (list != null && list.size() > 0) {
            return Integer.valueOf(list.get(0).getValue());
        }
        return defValue;
    }

    public Integer getKeyValue(String key, int defValue) {
        return getInt(key, defValue);
    }

    /**
     * Long
     */
    public void putKeyValue(String key, long value) {
        putLong(key, value);
    }

    public void putLong(String key, Long value) {
        String valueString = value.toString();
        KeyValueItem keyValueItem = new KeyValueItem(key, valueString, DataType.TYPE_LONG.ordinal());
        keyValueItem.insertOrReplaceToDb();
    }

    public Long getLong(String key, long defValue) {
        int type = DataType.TYPE_LONG.ordinal();
        List<KeyValueItem> list = KeyValueItem.listAll(key, type);
        if (list != null && list.size() > 0) {
            return Long.valueOf(list.get(0).getValue());
        }
        return defValue;
    }

    public Long getKeyValue(String key, long defValue) {
        return getLong(key, defValue);
    }

    /**
     * Float
     */
    public void putKeyValue(String key, float value) {
        putFloat(key, value);
    }

    public void putFloat(String key, Float value) {
        String valueString = value.toString();
        KeyValueItem keyValueItem = new KeyValueItem(key, valueString, DataType.TYPE_FLOAT.ordinal());
        keyValueItem.insertOrReplaceToDb();
    }

    public Float getFloat(String key, float defValue) {
        int type = DataType.TYPE_FLOAT.ordinal();
        List<KeyValueItem> list = KeyValueItem.listAll(key, type);
        if (list != null && list.size() > 0) {
            return Float.valueOf(list.get(0).getValue());
        }
        return defValue;
    }

    public Float getKeyValue(String key, float defValue) {
        return getFloat(key, defValue);
    }

    /**
     * Double
     */
    public void putKeyValue(String key, double value) {
        putDouble(key, value);
    }

    public void putDouble(String key, Double value) {
        String valueString = value.toString();
        KeyValueItem keyValueItem = new KeyValueItem(key, valueString, DataType.TYPE_DOUBLE.ordinal());
        keyValueItem.insertOrReplaceToDb();
    }


    public Double getDouble(String key, double defValue) {
        int type = DataType.TYPE_DOUBLE.ordinal();
        List<KeyValueItem> list = KeyValueItem.listAll(key, type);
        if (list != null && list.size() > 0) {
            return Double.valueOf(list.get(0).getValue());
        }
        return defValue;
    }

    public Double getKeyValue(String key, double defValue) {
        return getDouble(key, defValue);
    }

    /**
     * Date
     */
    public void putKeyValue(String key, Date value) {
        putDate(key, value);
    }

    public void putDate(String key, Date value) {
        String valueString = value.toString();
        KeyValueItem keyValueItem = new KeyValueItem(key, valueString, DataType.TYPE_DATE.ordinal());
        keyValueItem.insertOrReplaceToDb();
    }

    public Date getDate(String key, Date defValue) {
        int type = DataType.TYPE_DATE.ordinal();
        List<KeyValueItem> list = KeyValueItem.listAll(key, type);
        if (list != null && list.size() > 0) {
            return DateUtils.getDateTime(list.get(0).getValue());
        }
        return defValue;
    }

    public Date getKeyValue(String key, Date defValue) {
        return getDate(key, defValue);
    }

    /**
     * String List
     */
    public void putKeyValue(String key, List<String> value) {
        putStringList(key, value);
    }

    public void putStringList(String key, List<String> value) {
        String valueString = value.toString();
        KeyValueItem keyValueItem = new KeyValueItem(key, valueString, DataType.TYPE_STRINGLIST.ordinal());
        keyValueItem.insertOrReplaceToDb();
    }

    public List<String> getStringList(String key, List<String> defValues) {
        int type = DataType.TYPE_STRINGLIST.ordinal();
        List<KeyValueItem> list = KeyValueItem.listAll(key, type);
        if (list != null && list.size() > 0) {
            String stringList = list.get(0).getValue();
            stringList = stringList.substring(1, stringList.length() - 1);//String without bracket
            return Arrays.asList(stringList.split(","));
        }
        return defValues;
    }

    public List<String> getKeyValue(String key, List<String> defValues) {
        return getStringList(key, defValues);
    }

    public void delete(String key) {
        if (!TextUtils.isEmpty(key)) {
            KeyValueItem.deleteFromDatabase(key);
        } else {
            Logger.d("Please specify correct key");
        }
    }

    public long getCount() {
        return KeyValueItem.count();
    }

   /* public long getCount(Class<?> clazz) {
        int type = getType(clazz);
        if (type != -1) {
            return KeyValueItem.count(type);
        }
        return 0;
    }*/

}
