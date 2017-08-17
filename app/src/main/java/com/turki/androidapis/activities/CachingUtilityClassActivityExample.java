package com.turki.androidapis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.turki.androidapis.R;
import com.turki.androidapis.storage.caching.CachingOnInternalStorage;

/**
 * Testing activity for Caching Utility class.
 *
 * @author Turki Mahmoud
 */
public class CachingUtilityClassActivityExample extends AppCompatActivity {

    private static final String FILES_CACHE_NAME = "FILE_NAME";

    /**
     * TODO: Please read the following hints
     * 1- You shouldn't perform any long time operation in the Main Thread.
     * 2- Also you should separate the business logic from the presentation, So you can use MVP or MVVM
     * software design pattern. I didn't use it here to keep it simple when you trying to apply the sample and save time
     * <p>
     * Example: this cacheObject() and  restoreObject() should be separated in a manager class
     * which is dealing with the business logic ONLY
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_caching_utiltiy_class);

        // Caches a java Object internally within the application specific space.
        CachingOnInternalStorage.cacheObject(this, FILES_CACHE_NAME, new String("Cache Object On Internal Storage"));

        // Restores a java Object which is saved internally in the application specific space.
        CachingOnInternalStorage.restoreObject(this, FILES_CACHE_NAME);
    }
}
