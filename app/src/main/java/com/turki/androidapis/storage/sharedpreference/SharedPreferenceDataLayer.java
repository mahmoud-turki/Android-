package com.turki.androidapis.storage.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * It's Android utility class for saving any type of data in the SharedPreference
 *
 * @author Turki Mahmoud
 *
 */
public class SharedPreferenceDataLayer {

    // TODO: You can change the key of the SharedPreference as you like.
    private final static String SHARED_KEY = "SharedPreferenceDataLayer_Key";

    private static SharedPreferences setUp(Context context) {
        return context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
    }

    /**
     * @param context
     * @param values  Storing multiple string values in the SharedPreference
     */
    public static void saveStringPreferences(Context context, Map<String, String> values) {
        for (Map.Entry<String, String> item : values.entrySet())
            saveStringPreferences(context, item.getKey(), item.getValue());
    }

    /**
     * @param context
     * @param key
     * @param value   Storing single string values in the SharedPreference
     */
    public static void saveStringPreferences(Context context, String key, String value) {
        SharedPreferences sharedPreferences = setUp(context);
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defaultValue
     * @return the string value of specific key in the SharedPreference
     */
    public static String getStringPreferences(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = setUp(context);
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * @param context
     * @param values  Storing multiple integer values in the SharedPreference
     */
    public static void saveIntPreferences(Context context, Map<String, Integer> values) {
        for (Map.Entry<String, Integer> item : values.entrySet())
            saveIntPreferences(context, item.getKey(), item.getValue());
    }

    /**
     * @param context
     * @param key
     * @param value   Storing single integer value in the SharedPreference
     */
    public static void saveIntPreferences(Context context, String key, int value) {
        SharedPreferences sharedPreferences = setUp(context);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defaultValue
     * @return the integer value of specific key in the SharedPreference
     */
    public static int getIntPreferences(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = setUp(context);
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * @param context
     * @param values  Storing multiple float values in the SharedPreference
     */
    public static void saveFloatPreferences(Context context, Map<String, Float> values) {
        for (Map.Entry<String, Float> item : values.entrySet())
            saveFloatPreferences(context, item.getKey(), item.getValue());
    }

    /**
     * @param context
     * @param key
     * @param value   Storing single float value in the SharedPreference
     */
    public static void saveFloatPreferences(Context context, String key, float value) {
        SharedPreferences sharedPreferences = setUp(context);
        sharedPreferences.edit().putFloat(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defaultValue
     * @return the float value of specific key in the SharedPreference
     */
    public static float getFloatPreferences(Context context, String key, float defaultValue) {
        SharedPreferences sharedPreferences = setUp(context);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * @param context
     * @param values  Storing multiple boolean values in the SharedPreference
     */
    public static void saveBooleanPreferences(Context context, Map<String, Boolean> values) {
        for (Map.Entry<String, Boolean> item : values.entrySet())
            saveBooleanPreferences(context, item.getKey(), item.getValue());
    }

    /**
     * @param context
     * @param key
     * @param value   Storing single boolean value in the SharedPreference
     */
    public static void saveBooleanPreferences(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = setUp(context);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defaultVlaue
     * @return the boolean value of specific key in the SharedPreference
     */
    public static boolean getBooleanPreferences(Context context, String key, boolean defaultVlaue) {
        SharedPreferences sharedPreferences = setUp(context);
        return sharedPreferences.getBoolean(key, defaultVlaue);
    }

    /**
     * @param context
     * @param values  Storing multiple long values in the SharedPreference
     */
    public static void saveLongPreferences(Context context, Map<String, Long> values) {
        for (Map.Entry<String, Long> item : values.entrySet())
            saveLongPreferences(context, item.getKey(), item.getValue());
    }

    /**
     * @param context
     * @param key
     * @param value   Storing single long value in the SharedPreference
     */
    public static void saveLongPreferences(Context context, String key, Long value) {
        SharedPreferences sharedPreferences = setUp(context);
        sharedPreferences.edit().putLong(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param defaultValue
     * @return the long value of specific key in the SharedPreference
     */
    public static Long getLongPreferences(Context context, String key, Long defaultValue) {
        SharedPreferences sharedPreferences = setUp(context);
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * @param context
     * @return true or false for clearing the cached SharedPreference
     */
    public static boolean clearSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = setUp(context);
        return sharedPreferences.edit().clear().commit();
    }
}

