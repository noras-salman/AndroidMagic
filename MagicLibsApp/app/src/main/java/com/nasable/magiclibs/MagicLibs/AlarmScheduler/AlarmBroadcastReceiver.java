package com.nasable.magiclibs.MagicLibs.AlarmScheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



public abstract class AlarmBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        action();
        whenDone(context);
    }

    /**
     * @return The interval in milliseconds
     **/
    public abstract long getInterval();

    /***
     * The action to be done when an alarm is triggered
     **/
    public abstract void action();


    /**
     * when done call setAlarm(context,getInterval());
     **/
    public abstract void whenDone(Context context);

}
