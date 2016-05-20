package com.android.keyvaluestorage.helper;

import java.util.Date;

/**
 * Meta data describing a property mapped to a database column.
 */
public class SQLColumn {
    public final int ordinal;
    public final String type;
    public final String name;
    public final boolean primaryKey;
    public final String columnName;
    public final boolean isUnique;

    public SQLColumn(int ordinal, Class<?> type, String name, boolean primaryKey, String columnName, boolean isUnique) {
        this.ordinal = ordinal;
        if (type.equals(Long.class) || type.equals(Integer.class)) {
            this.type = "INTEGER";
        } else if (type.equals(Boolean.class)) {
            this.type = "BOOLEAN";
        } else if (type.equals(Float.class) || type.equals(Double.class)) {
            this.type = "REAL";
        } else if (type.equals(String.class)) {
            this.type = "TEXT";
        } else if (type.equals(Date.class)) {
            this.type = "DATETIME";
        } else {
            this.type = "BLOB";
        }
        this.name = name;
        this.primaryKey = primaryKey;
        this.columnName = columnName;
        this.isUnique = isUnique;
    }
}