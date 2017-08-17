package com.turki.androidapis.storage.sqlite.ApplicationTables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.turki.androidapis.application.ApplicationController;
import com.turki.androidapis.storage.sqlite.DataBaseGenericCreation.AbstractTable;
import com.turki.androidapis.storage.sqlite.DataBaseGenericCreation.DatabaseManager;
import com.turki.androidapis.storage.sqlite.entities.UserEntity;

/**
 * @author Turki Mahmoud
 */
public class UserTable extends AbstractTable<UserEntity> {

    private static UserTable sInstance;

    // Table name
    private static final String TABLE_USER = "UserTable";

    private static final class Fields implements BaseColumns {
        // Column names
        private static final String USER_ID = BaseColumns._ID;
        private static final String USER_NAME = "userName";
    }

    // Create Table Message
    private static final String CREATE_TABLE_FRIEND = "CREATE TABLE " + TABLE_USER
            + "("
            + Fields.USER_ID + " INTEGER PRIMARY KEY,"
            + Fields.USER_NAME + " TEXT"
            + ")";

    public static UserTable getInstance() {
        if (sInstance == null)
            sInstance = new UserTable();
        return sInstance;
    }

    public int getCount() {
        String query = "select * from " + TABLE_USER;
        Cursor cursor = DatabaseManager.getInstance(ApplicationController.getInstance().getApplicationContext()).getReadableDatabase()
                .rawQuery(query, null);
        return cursor.getCount();
    }

    private UserTable() {
        super(DatabaseManager.getInstance(ApplicationController.getInstance().getApplicationContext()),
                ApplicationController.getInstance().getApplicationContext());
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void create(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_TABLE_FRIEND);
    }

    @Override
    protected void upgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    @Override
    protected String getTableName() {
        // TODO Auto-generated method stub
        return TABLE_USER;
    }

    @Override
    protected String[] getProjection() {
        // TODO Auto-generated method stub
        return new String[]{Fields.USER_ID, Fields.USER_NAME};
    }

    @Override
    protected ContentValues getContentValues(UserEntity entity) {
        // TODO Auto-generated method stub
        ContentValues values = new ContentValues();
        values.put(Fields.USER_ID, entity.getId());
        values.put(Fields.USER_NAME, entity.getName());
        return values;
    }

    @Override
    protected UserEntity getObjFromCursor(Cursor cursor) {
        // TODO Auto-generated method stub
        UserEntity object = new UserEntity();
        object.setId(cursor.getInt(cursor.getColumnIndex(Fields.USER_ID)));
        object.setName(cursor.getString(cursor.getColumnIndex(Fields.USER_NAME)));
        return object;
    }
}