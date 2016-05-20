package com.android.keyvaluestorage.dbmodels;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.android.keyvaluestorage.helper.DBHelper;
import com.android.keyvaluestorage.helper.QueryBuilder;
import com.android.keyvaluestorage.helper.SQLColumn;
import com.android.keyvaluestorage.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage table KEY_VALUE
 */
public class KeyValueItem extends BaseItem {

    public static final String TABLENAME = "KEY_VALUE";

    /**
     * Columns
     */
    private String key;
    private String value;
    private int type;

    /**
     * Necessary no argument constructor.
     */
    public KeyValueItem() {
    }

    /**
     * Constructor used by Helper.
     *
     * @param key   Unique Key
     * @param value Value passed
     * @param type  type of value
     */
    public KeyValueItem(String key, String value, int type) {
        super();
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "KeyValueItem [key=" + key + ", value=" + value + "]";
    }

    /**
     * Properties of entity.
     * Can be used for referencing column names.
     */
    public static class Columns {

        private static List<SQLColumn> mColumns = new ArrayList<>();

        public final static SQLColumn KEY = new SQLColumn(0, String.class, "key", true, "KEY", false);
        public final static SQLColumn VALUE = new SQLColumn(1, String.class, "value", false, "VALUE", false);
        public final static SQLColumn TYPE = new SQLColumn(2, Integer.class, "type", false, "TYPE", false);

        public static List<SQLColumn> getColumns() {
            if (mColumns.isEmpty()) {
                mColumns.add(KEY);
                mColumns.add(VALUE);
                mColumns.add(TYPE);
            }
            return mColumns;
        }

    }
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    /**
     * Creates the underlying database table.
     *
     * @param db SQLiteDatabase
     */
    public static void createTable(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " + TABLENAME + " ";

        List<SQLColumn> columns = Columns.getColumns();

        createQuery += "(";
        for (SQLColumn column : columns) {
            createQuery += column.columnName + " " + column.type;
            // If primary key
            if (column.primaryKey) {
                createQuery += " PRIMARY KEY";
            }
            //If Unique
            if (column.isUnique) {
                createQuery += " UNIQUE";
            }
            // Adding comma for all items except last
            if (columns.indexOf(column) != columns.size() - 1) {
                createQuery += ", ";
            }
        }
        createQuery += ");";

        Logger.i("Query: " + createQuery);
        db.execSQL(createQuery);
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + TABLENAME;
        db.execSQL(sql);
    }

    /**
     * List functions
     */
    public static List<KeyValueItem> listAll() {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.select(KeyValueItem.TABLENAME);
        return DBHelper.getInstance().listResult(KeyValueItem.class, queryBuilder.list());
    }

    public static List<KeyValueItem> listAll(int type) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.select(KeyValueItem.TABLENAME);
        queryBuilder.where(Columns.TYPE.columnName, (type + ""), true);
        return DBHelper.getInstance().listResult(KeyValueItem.class, queryBuilder.list());
    }

    public static List<KeyValueItem> listAll(String key, int type) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.select(KeyValueItem.TABLENAME);
        queryBuilder.where(KeyValueItem.Columns.KEY.columnName, key, true);
        queryBuilder.where(KeyValueItem.Columns.TYPE.columnName, (type + ""), true);
        return DBHelper.getInstance().listResult(KeyValueItem.class, queryBuilder.list());
    }

    /**
     * Reads {@link KeyValueItem} in the underlying database table.
     *
     * @return {@link KeyValueItem}
     */
    @Override
    public KeyValueItem readFromCursor(Cursor cursor, int offset) {
        KeyValueItem message = new KeyValueItem(
                cursor.isNull(Columns.KEY.ordinal) ? null : cursor.getString(Columns.KEY.ordinal),
                cursor.isNull(Columns.VALUE.ordinal) ? null : cursor.getString(Columns.VALUE.ordinal),
                cursor.isNull(Columns.TYPE.ordinal) ? null : cursor.getInt(Columns.TYPE.ordinal));
        return message;
    }

    /**
     * Inserts {@link KeyValueItem} in the underlying database table.
     */
    @Override
    public void insertOrReplaceToDb() {
        boolean insertOrReplace = isInsertOrReplace();//false-INSERT true-REPLACE
        if (insertOrReplace) {
            String whereClause = " " + Columns.KEY.columnName + "=?";
            DBHelper.getInstance().update(TABLENAME, getContentValues(), whereClause, key);
        } else {
            DBHelper.getInstance().insert(TABLENAME, getContentValues());
        }
    }

    private boolean isInsertOrReplace() {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.select(KeyValueItem.TABLENAME);
        queryBuilder.where(KeyValueItem.Columns.KEY.columnName, key, true);
        List<KeyValueItem> list = DBHelper.getInstance().listResult(KeyValueItem.class, queryBuilder.list());
        return list != null && list.size() > 0;
    }

    /**
     * Deletes {@link KeyValueItem} from the underlying database table.
     */
    public static void deleteAllFromDatabase() {
        DBHelper.getInstance().delete(TABLENAME);
    }

    public static void deleteAllFromDatabase(int type) {
        String whereClause = " " + Columns.TYPE.columnName + "=?";
        DBHelper.getInstance().delete(TABLENAME, whereClause, (type + ""));
    }

    public static void deleteFromDatabase(String key) {
        String whereClause = " " + Columns.KEY.columnName + "=?";
        DBHelper.getInstance().delete(TABLENAME, whereClause, key);
    }

    /**
     * Count functions
     */
    public static long count() {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.selectCount(KeyValueItem.TABLENAME);
        return DBHelper.getInstance().countResult(queryBuilder.list());
    }

    public static long count(int type) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.selectCount(KeyValueItem.TABLENAME);
        queryBuilder.where(Columns.TYPE.columnName, type + "", true);
        return DBHelper.getInstance().countResult(queryBuilder.list());
    }

    private ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(Columns.KEY.columnName, key);
        values.put(Columns.VALUE.columnName, value);
        values.put(Columns.TYPE.columnName, type);
        return values;
    }
}
