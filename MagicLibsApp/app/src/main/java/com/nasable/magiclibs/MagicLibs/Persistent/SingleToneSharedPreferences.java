package com.nasable.magiclibs.MagicLibs.Persistent;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;



public abstract class SingleToneSharedPreferences {

    public static String DEFAULT_STRING = null;
    public static int DEFAULT_INT = -1;
    public static long DEFAULT_LONG = -1;
    public static float DEFAULT_FLOAT = -1;
    public static boolean DEFAULT_BOOLEAN = false;

    public static String DEFAULT_VERSION_KEY =  "VERSION";
    private String APP_PREFERENCES = "SingleToneSharedPreferences";//BuildConfig.APPLICATION_ID;
    private Context context;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    public SingleToneSharedPreferences(Context context) {
        this.context = context;
        this.sharedpreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        this.editor = sharedpreferences.edit();
        upgradeCheck();
    }


    private void upgradeCheck(){
        if(getInt(DEFAULT_VERSION_KEY)!=getVersion()){
            clear();
            save(DEFAULT_VERSION_KEY,getVersion());
            buildInit();
        }
    }

    private void buildInit(){
        List<SinglePreference> preferences=getSettings(new ArrayList<SinglePreference>());
        for(SinglePreference s:preferences){
            if(s.isBool()){
                save(s.getKeyName(),s.getDefBool());
            }else if(s.isInt()){
                save(s.getKeyName(),s.getDefInt());
            }else if(s.isFloat()){
                save(s.getKeyName(),s.getDefFloat());
            }else if(s.isString()){
                save(s.getKeyName(),s.getDefString());
            }
        }
    }

    /**
     * When upgrading the version you can update the default values with the shipped app
     * default value DEFAULT_VERSION
     * @return the version
     */
    public abstract int getVersion();

    /**
     * For initializing the preferences
     * @param preferences
     * @return preferences with SinglePreference
     */
    public abstract List<SinglePreference> getSettings(List<SinglePreference> preferences);


    public boolean getBool(String key) {
        return get(key, DEFAULT_BOOLEAN);
    }


    public int getInt(String key) {
        return get(key, DEFAULT_INT);
    }

    public String getString(String key) {
        return get(key, DEFAULT_STRING);
    }

    public float getFloat(String key) {
        return get(key, DEFAULT_FLOAT);
    }

    public float getLong(String key) {
        return get(key, DEFAULT_LONG);
    }

    /**
     * SDK Specification
     **/

    protected String get(String key, String defaultValue) {
        return sharedpreferences.getString(key, defaultValue);
    }

    protected void save(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    protected int get(String key, int defaultValue) {
        return sharedpreferences.getInt(key, defaultValue);
    }

    protected void save(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    protected boolean get(String key, boolean defaultValue) {
        return sharedpreferences.getBoolean(key, defaultValue);
    }

    protected void save(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    protected long get(String key, long defaultValue) {
        return sharedpreferences.getLong(key, defaultValue);
    }

    protected void save(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    protected float get(String key, float defaultValue) {
        return sharedpreferences.getFloat(key, defaultValue);
    }

    protected void save(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * @param key
     * @return Returns if that key exists
     */
    protected boolean contains(String key) {
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