package com.turki.androidapis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.turki.androidapis.R;
import com.turki.androidapis.application.ApplicationController;
import com.turki.androidapis.storage.filestorage.ExternalFileSaveDataLayer;
import com.turki.androidapis.storage.filestorage.InternalFileSaveDataLayer;

/**
 * Testing activity for FileStorage Utility class.
 *
 * @author Turki Mahmoud
 */
public class FileStorageUtilityClassActivityExample extends AppCompatActivity {

    private static final String INTERNAL_FILES_NAME = "INTERNAL_FILE_NAME";
    private static final String EXTERNAL_FILES_NAME = "EXTERNAL_FILE_NAME";

    /**
     * TODO: Please read the following hints
     * 1- You shouldn't perform any long time operation in the Main Thread.
     * 2- Also you should separate the business logic from the presentation, So you can use MVP or MVVM
     * software design pattern. I didn't use it here to keep it simple when you trying to apply the sample and save time
     * <p>
     * Example: this saveObjectInternally(), saveObjectExternally() and  restoreObjectExternally() should be
     * separated in a manager class which is dealing with the business logic ONLY
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_file_storage_utility_class);

        // Save a java Object internally within the application specific space.
        saveObjectInternally();
        // Save a java Object externally within the application specific space.
        saveObjectExternally();
        // Restores a java Object which is saved externally in the application specific space.
        restoreObjectExternally();
    }

    /**
     * Test for save a java Object internally within the application specific space.
     */
    private void saveObjectInternally() {
        ApplicationController.getInstance().runInBackground(new Runnable() {
            @Override
            public void run() {
                try {
                    InternalFileSaveDataLayer.saveObject(getApplicationContext(), INTERNAL_FILES_NAME, new String("Save Object In Internal File"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Test for save a java Object externally within the application specific space.
     */
    private void saveObjectExternally() {
        ApplicationController.getInstance().runInBackground(new Runnable() {
            @Override
            public void run() {
                try {
                    ExternalFileSaveDataLayer.saveObject(getApplicationContext(), EXTERNAL_FILES_NAME, new String("Save Object In External File"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Test for restores a java Object which is saved externally in the application specific space.
     */
    private void restoreObjectExternally() {
        ApplicationController.getInstance().runInBackground(new Runnable() {
            @Override
            public void run() {
                try {
                    ExternalFileSaveDataLayer.getObject(EXTERNAL_FILES_NAME);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
