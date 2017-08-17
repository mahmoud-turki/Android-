package com.turki.androidapis.storage.sqlite.DataBaseGenericCreation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * A base abstraction of any database table.<br><br>
 * <b> Note: </b> This table's contract must implement {@link BaseColums}, meaning that the id column of the table must be
 * {@link BaseColums#ID}
 *
 * @param <T> the type on which table is based on.
 * @author Turki Mahmoud
 */
public abstract class AbstractTable<T> {

    private SQLiteDatabase database;
    private SQLiteOpenHelper sqlHelper;

    /**
     * Creates your table using the passed in {@link SQLiteDatabase} object
     *
     * @param database
     */
    protected abstract void create(SQLiteDatabase database);

    /**
     * Upgrade handling of your table
     *
     * @param database
     * @param oldVersion
     * @param newVersion
     */

    protected abstract void upgrade(SQLiteDatabase database, int oldVersion, int newVersion);

    /**
     * @return Your table name
     */
    protected abstract String getTableName();

    /**
     * @return The projection of your table [All column names]
     */

    protected abstract String[] getProjection();

    /**
     * Returns a populated {@link ContentValues} object from the passed in entity.
     *
     * @param entity
     * @return
     */
    protected abstract ContentValues getContentValues(T entity);

    /**
     * Constructs the entity returned from the passed in {@link Cursor}
     *
     * @param cursor Cursor that is moved to right position holding data for the returned entity
     * @return
     */
    protected abstract T getObjFromCursor(Cursor cursor);

    public AbstractTable(SQLiteOpenHelper sqlHelper, Context context) {
        DatabaseManager.getInstance(context).addTable(this);
        this.sqlHelper = sqlHelper;
    }

    protected SQLiteDatabase getDatabase() {
        return sqlHelper.getWritableDatabase();
    }

    /**
     * Returns a single row from your table with the passed in id.
     *
     * @param id
     * @return
     */
    public T get(int id) {
        Cursor cursor = getDatabase().query(getTableName(), getProjection(),
                BaseColumns._ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        T obj = null;
        if (cursor.moveToFirst())
            obj = getObjFromCursor(cursor);
        return obj;
    }

    /**
     * Returns a list with the specified selection clause and arguments.
     *
     * @param selection
     * @param selectionArgs
     * @return
     */
    public List<T> getBySelection(String selection, String[] selectionArgs) {
        return getList(selection, selectionArgs);
    }

    /**
     * Returns a list of all rows in your table
     *
     * @return
     */
    public List<T> getAll() {
        return getList(null, null);
    }

    private List<T> getList(String selection, String[] selectionArgs) {
        Cursor cursor = getDatabase().query(getTableName(), getProjection(), selection, selectionArgs, null, null, null);
        List<T> list = new ArrayList<T>(cursor.getCount() >= 0 ? cursor.getCount() : 10/* Normal initial capacity for ArrayList  */);
        if ((cursor.moveToFirst())) {
            do {
                list.add(getObjFromCursor(cursor));
            }
            while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * Inserts a single entity in your table
     *
     * @param obj
     * @return
     */
    public boolean insert(T obj) {
        return getDatabase().insert(getTableName(), null, getContentValues(obj)) != -1;
    }

    /**
     * Inserts a list of entity in your table
     *
     * @param objList list to be inserted
     */
    public void insert(List<T> objList) {
        for (T obj : objList) {
            insert(obj);
        }
    }

    /**
     * Updates a row with passed id in your table with the passed in values from entity
     *
     * @param obj Updated values
     * @param id  id of the row which will be updated
     * @return true if any rows has been updated, false otherwise.
     */
    public boolean update(T obj, int id) {
        return getDatabase().update(getTableName(), getContentValues(obj),
                BaseColumns._ID + " = ?", new String[]{String.valueOf(id)}) > 0;
    }

    /**
     * Deletes a specific item from the table with the passed in id
     *
     * @param id
     */
    public void delete(int id) {
        getDatabase().delete(getTableName(), BaseColumns._ID + " = ?", new String[]{String.valueOf(id)});
    }

    /**
     * Deletes data from table based on the selection clause and arguments passed in
     *
     * @param selection
     * @param selectionArgs
     */
    public void delete(String selection, String[] selectionArgs) {
        getDatabase().delete(getTableName(), selection, selectionArgs);
    }

    /**
     * Deletes all data in your table
     */
    public void deleteAll() {
        getDatabase().delete(getTableName(), null, null);
    }
}
