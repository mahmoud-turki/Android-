package com.turki.androidapis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.turki.androidapis.R;
import com.turki.androidapis.storage.sharedpreference.SharedPreferenceDataLayer;

import java.util.HashMap;
import java.util.Map;

/**
 * Testing activity for SharedPreferenceDataLayer class.
 *
 * @author Turki Mahmoud
 */
public class SharedPreferenceUtilityClassActivityExample extends AppCompatActivity {

    /**
     * TODO: Please read the following hints
     * 1- You shouldn't perform any long time operation in the Main Thread.
     * 2- Also you should separate the business logic from the presentation, So you can use MVP or MVVM
     * software design pattern. I didn't use it here to keep it simple when you trying to apply the sample and save time
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_shared_preference_utility_class);

        Map<String, Boolean> map = new HashMap<>();
        map.put("BOOLEAN_KEY_1", true);
        map.put("BOOLEAN_KEY_2", true);

        // Storing multiple boolean values in the SharedPreference
        SharedPreferenceDataLayer.saveBooleanPreferences(this, map);
        // Storing single boolean value in the SharedPreference
        SharedPreferenceDataLayer.saveBooleanPreferences(this, "BOOLEAN_KEY", true);
        // Return the boolean value of specific key in the SharedPreference
        SharedPreferenceDataLayer.getBooleanPreferences(this, "BOOLEAN_KEY", false);
    }
}
