package com.turki.androidapis.storage.sqlite.DataBaseGenericCreation;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class to manage database operations [Creation - Upgrade] and manage connections to database.<br><br>
 * <b>Usage Notes:</b><br>
 * <ul>
 * <li>Implement {@link DatabaseManager#initializeTables()} with instantiation of all your database tables
 * which should be children of {@link AbstractTable}</li>
 * <li>Call {@link DatabaseManager#initializeTables()} at the start of your application, your {@link Application#onCreate()}
 * for example</li>
 * </ul>
 *
 * @author Turki Mahmoud
 */
public class DatabaseManager extends SQLiteOpenHelper {
    /**
     * name of the database file
     */
    private static final String DATABASE_NAME = "app.db";
    /**
     * database version that should be incremented if there is any structural changes
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * List of registered tables in the application
     */
    private List<AbstractTable<?>> dbTables;

    private static DatabaseManager instance;
    private SQLiteDatabase db;

    /**
     * @return An instance of database manager.
     */
    public synchronized static DatabaseManager getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseManager(context);
        return instance;
    }

    private DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbTables = new ArrayList<AbstractTable<?>>();
    }

    public void initializeTables() {
        // TODO: initialize instances of all your tables here
    }

    public void clearAppDatabase() {
        for (AbstractTable<?> table : dbTables) {
            table.deleteAll();
        }
    }

    protected SQLiteDatabase getDb() {
        if (db == null)
            db = getWritableDatabase();
        return db;
    }

    protected void addTable(AbstractTable<?> table) {
        dbTables.add(table);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            for (AbstractTable<?> table : dbTables) {
                table.create(db);
            }
        } catch (SQLException e) {
            Log.e("DatabaseManagerOnCreate", "An error has occurred while creating database tables");
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            if (oldVersion < newVersion) {
                for (AbstractTable<?> table : dbTables) {
                    table.upgrade(db, oldVersion, newVersion);
                }
            } else {
                for (AbstractTable<?> table : dbTables) {
                    dropTable(db, table.getTableName());
                    table.create(db);
                }
            }
        } catch (SQLException e) {
            Log.e("DatabaseManagerUpgrade", "An error has occurred while upgrading database tables");
            e.printStackTrace();
        }
    }

    protected static void dropTable(SQLiteDatabase db, String tableName) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName + ";");
    }

    protected static void renameTable(SQLiteDatabase db, String table, String newTable) {
        db.execSQL("ALTER TABLE " + table + "RENAME TO " + newTable + ";");
    }
}
