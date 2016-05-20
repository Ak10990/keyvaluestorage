package com.android.keyvaluestorage.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.keyvaluestorage.dbmodels.BaseItem;
import com.android.keyvaluestorage.dbmodels.KeyValueItem;
import com.android.keyvaluestorage.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "KeyValueDb.sqlite";
    private static final int DB_VERSION = 1;

    private static DBHelper mInstance = null;
    private SQLiteDatabase mDataBase = null;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mDataBase = getWritableDatabase();
    }

    /**
     * {@link DBHelper} is initialized here
     *
     * @param context Context
     */
    public static void init(Context context) {
        mInstance = new DBHelper(context);
    }

    /**
     * To create the singleton instance of {@link DBHelper}.
     */
    public static DBHelper getInstance() {
        if (mInstance == null) {
            Logger.e("Did you forgot to call DBHelper.init() ?");
        }
        return mInstance;
    }

    /**
     * Creating all tables
     *
     * @param db SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    public void createTables(SQLiteDatabase db) {
        KeyValueItem.createTable(db);
    }

    public void dropAllTables(SQLiteDatabase db) {
        KeyValueItem.dropTable(db, true);
    }

    /**
     * Handling onUpgrade
     *
     * @param db         SQLiteDatabase
     * @param oldVersion Upgrade from
     * @param newVersion Upgrade to
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAllTables(db);
        createTables(db);
    }

    /**
     * Select method
     */
    public <T extends BaseItem> List<T> listResult(Class<T> clazzType, String query, String... whereArgs) {
        Cursor cursor = rawQuery(query, whereArgs);
        BaseItem.SubClassContainer<T> container = new BaseItem.SubClassContainer<>();
        return cursor == null ? null : loadAllFromCursor(cursor, container.createContents(clazzType));
    }

    public long countResult(String query, String... whereArgs) {
        Cursor c = rawQuery(query, whereArgs);
        c.moveToFirst();
        return c.getInt(0);
    }

    /**
     * Insert method
     */
    public long insert(String table, ContentValues values) {
        if (mDataBase != null && mDataBase.isOpen()) {
            mDataBase.beginTransaction();
            long rowId = -1;
            try {
                rowId = mDataBase.insert(table, null, values);
                mDataBase.setTransactionSuccessful();
            } finally {
                mDataBase.endTransaction();
            }
            return rowId;
        } else {
            return -1;
        }
    }

    /**
     * Update method
     */
    public int update(String table, ContentValues contentValues, String whereClause, String... whereArgs) {
        if (mDataBase != null && mDataBase.isOpen()) {
            return mDataBase.update(table, contentValues, whereClause, whereArgs);
        }
        return -1;
    }

    /**
     * Delete Method
     */
    public void delete(String table) {
        if (mDataBase != null && mDataBase.isOpen()) {
            mDataBase.delete(table, null, null);
        }
    }

    public void delete(String table, String whereClause, String... whereArgs) {
        if (mDataBase != null && mDataBase.isOpen()) {
            mDataBase.delete(table, whereClause, whereArgs);
        }
    }

    /**
     * Iterates a Cursor and prints its contents.
     */
    public void logTable(String tableName) {
        if (mDataBase != null && mDataBase.isOpen()) {
            Cursor c = mDataBase.rawQuery("SELECT * FROM " + tableName, null);
            c.moveToFirst();
            Logger.d("*** Cursor Begin *** " + " Results:" + c.getCount() + " Columns: " + c.getColumnCount());
            // Print column names
            String rowHeaders = "|| ";
            for (int i = 0; i < c.getColumnCount(); i++) {
                rowHeaders = rowHeaders.concat(c.getColumnName(i) + " || ");
            }
            Logger.d("COLUMNS " + rowHeaders);
            // Print records
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String rowResults = "|| ";
                for (int i = 0; i < c.getColumnCount(); i++) {
                    rowResults = rowResults.concat(c.getString(i) + " || ");
                }
                Logger.d("Row " + c.getPosition() + ": " + rowResults);
                c.moveToNext();
            }
            Logger.d("*** Cursor End ***");
            c.close();
        }
    }

    /**
     * Utility methods
     */
    /**
     * @param query select query
     * @return - Cursor with the results
     * @throws SQLException sql exception
     */
    private Cursor rawQuery(final String query, final String[] selectionArgs) throws SQLException {
        return mDataBase.rawQuery(query, selectionArgs);
    }

    private <T extends BaseItem> List<T> loadAllFromCursor(Cursor cursor, T clazz) {
        return loadAllFromCursor(cursor, clazz, 0);
    }

    private <T extends BaseItem> List<T> loadAllFromCursor(Cursor cursor, T clazz, int offset) {
        try {
            int count = cursor.getCount();
            List<T> list = new ArrayList<>(count);
            if (cursor.moveToFirst()) {
                do {
                    BaseItem clazzRead = clazz.readFromCursor(cursor, offset);
                    if (clazzRead != null)
                        list.add((T) clazzRead);
                } while (cursor.moveToNext());
            }
            return list;
        } finally {
            cursor.close();
        }
    }
}
