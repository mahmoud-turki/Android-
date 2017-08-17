package com.turki.androidapis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.turki.androidapis.R;
import com.turki.androidapis.application.ApplicationController;
import com.turki.androidapis.storage.sqlite.ApplicationTables.UserTable;
import com.turki.androidapis.storage.sqlite.entities.UserEntity;

import java.util.List;

/**
 * Testing activity for SQLite Utility class.
 *
 * @author Turki Mahmoud
 */
public class SQLiteUtilityClassActivityExample extends AppCompatActivity {

    /**
     * TODO: Please read the following hints
     * 1- You shouldn't perform any long time operation in the Main Thread.
     * 2- Also you should separate the business logic from the presentation, So you can use MVP or MVVM
     * software design pattern. I didn't use it here to keep it simple when you trying to apply the sample and save time.
     * <p>
     * Example: this restoreUsersFromSQLiteDataBase() should be separated in a manager class
     * which is dealing with the business logic ONLY
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_sqlite_utility_class);
        // Restores list of uses which is saved in the loacl SQLite database.
        restoreUsersFromSQLiteDataBase();
    }


    /**
     * Restores list of uses which is saved in the loacl SQLite database.
     */
    private void restoreUsersFromSQLiteDataBase() {
        ApplicationController.getInstance().runInBackground(new Runnable() {
            @Override
            public void run() {
                List<UserEntity> cachedUsers = UserTable.getInstance().getAll();
            }
        });
    }
}
