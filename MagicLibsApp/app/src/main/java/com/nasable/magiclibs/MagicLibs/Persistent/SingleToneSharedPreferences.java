package com.nasable.magiclibs.MagicLibs.Persistent;

import android.content.Context;
import android.content.SharedPreferences;



public class SingleToneSharedPreferences {



    public static String DEFAULT_STRING=null;
    public static int DEFAULT_INT=-1;
    public static long DEFAULT_LONG=-1;
    public static float DEFAULT_FLOAT=-1;


    private String APP_PREFERENCES = "SingleToneSharedPreferences" ;//BuildConfig.APPLICATION_ID;
    private Context context;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    public SingleToneSharedPreferences(Context context) {
        this.context = context;
        this.sharedpreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        this.editor = sharedpreferences.edit();
    }




    /** SDK Specification **/

    private String get(String key,String defaultValue) {
        return sharedpreferences.getString(key, defaultValue);
    }

    private void save(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    private int get(String key, int defaultValue) {
        return sharedpreferences.getInt(key, defaultValue);
    }

    private void save(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    private boolean get(String key, boolean defaultValue) {
        return sharedpreferences.getBoolean(key, defaultValue);
    }

    private void save(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    private long get(String key, long defaultValue) {
        return sharedpreferences.getLong(key, defaultValue);
    }

    private void save(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    private float get(String key, float defaultValue) {
        return sharedpreferences.getFloat(key, defaultValue);
    }

    private void save(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * @param key
     * @return Returns if that key exists
     */
    public boolean contains(String key) {
        return sharedpreferences.contains(key);
    }

    /**
     * Clear all the preferences
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

}