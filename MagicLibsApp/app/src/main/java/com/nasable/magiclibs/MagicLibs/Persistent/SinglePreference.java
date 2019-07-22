package com.nasable.magiclibs.MagicLibs.Persistent;

public class SinglePreference {
    public static final int TYPE_INT = 1;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_STRING = 3;
    public static final int TYPE_BOOLEAN = 4;

    private String KEY_NAME;
    private int KEY_TYPE;

    private int KEY_DEF_INT;
    private boolean KEY_DEF_BOOL;
    private float KEY_DEF_FLOAT;
    private String KEY_DEF_STRING;

    public SinglePreference(String KEY_NAME, int KEY_DEF_INT) {
        this.KEY_NAME = KEY_NAME;
        this.KEY_TYPE = TYPE_INT;
        this.KEY_DEF_INT = KEY_DEF_INT;
    }

    public SinglePreference(String KEY_NAME, boolean KEY_DEF_BOOL) {
        this.KEY_NAME = KEY_NAME;
        this.KEY_TYPE = TYPE_BOOLEAN;
        this.KEY_DEF_BOOL = KEY_DEF_BOOL;
    }

    public SinglePreference(String KEY_NAME, float KEY_DEF_FLOAT) {
        this.KEY_NAME = KEY_NAME;
        this.KEY_TYPE = TYPE_FLOAT;
        this.KEY_DEF_FLOAT = KEY_DEF_FLOAT;
    }

    public SinglePreference(String KEY_NAME, String KEY_DEF_STRING) {
        this.KEY_NAME = KEY_NAME;
        this.KEY_TYPE = TYPE_STRING;
        this.KEY_DEF_STRING = KEY_DEF_STRING;
    }



    public String getKeyName() {
        return KEY_NAME;
    }

    public int getDefInt() {
        return KEY_DEF_INT;
    }

    public boolean getDefBool() {
        return KEY_DEF_BOOL;
    }

    public float getDefFloat() {
        return KEY_DEF_FLOAT;
    }

    public String getDefString() {
        return KEY_DEF_STRING;
    }

    public boolean isInt(){
        return KEY_TYPE==TYPE_INT;
    }

    public boolean isBool(){
        return KEY_TYPE==TYPE_BOOLEAN;
    }

    public boolean isFloat(){
        return KEY_TYPE==TYPE_FLOAT;
    }

    public boolean isString(){
        return KEY_TYPE==TYPE_STRING;
    }



}
