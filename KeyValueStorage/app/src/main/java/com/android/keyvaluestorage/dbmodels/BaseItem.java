package com.android.keyvaluestorage.dbmodels;

import android.database.Cursor;

import java.io.Serializable;

/**
 * Class containing basic abstract functions for database.
 */
public abstract class BaseItem implements Serializable {
    
    public abstract void insertOrReplaceToDb();

    public abstract <T extends BaseItem> T readFromCursor(Cursor cursor, int offset);
    
    public static class SubClassContainer<T> {
        public T createContents(Class<T> clazz) {
            try {
                return clazz.newInstance();
            } catch (Exception exception) {
                return null;
            }
        }
    }
}