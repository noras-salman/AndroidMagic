package com.nasable.magiclibs.MagicLibs.Persistent;

import android.content.Context;

import java.util.List;

public class AppSettingsExample extends SingleToneSharedPreferences {


    public AppSettingsExample(Context context) {
        super(context);
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public List<SinglePreference> getSettings(List<SinglePreference> preferences) {
        preferences.add(new SinglePreference(IS_SIGNED_IN,false));
        return preferences;
    }

    private final String IS_SIGNED_IN="IS_SIGNED_IN";

    public boolean isSignedIn(){
        return getBool(IS_SIGNED_IN);
    }

    public void setIsSignedIn(boolean value){
        save(IS_SIGNED_IN,value);
    }
}
