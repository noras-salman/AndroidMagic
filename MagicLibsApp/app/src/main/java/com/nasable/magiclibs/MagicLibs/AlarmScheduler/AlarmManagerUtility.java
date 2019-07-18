package com.nasable.magiclibs.MagicLibs.AlarmScheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



public abstract class AlarmManagerUtility  extends BroadcastReceiver {



    /***
     * The action to be done when an alarm is triggered
     **/
    public abstract void action();

    /**
     * @return The interval in milliseconds
     **/
    public abstract long getInterval();

    /**
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        action();
        setAlarm(context);
    }

    /**
     *
     * @param context
     */
    public void setAlarm(Context context) {
        setAlarm(context, getInterval());
    }

    /**
     *
     * @param context
     */
    public void cancelAlarm(Context context) {
        Log.d("AlarmManagerUtility", "cancelAlarm");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(context, this.getClass());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 1, myIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

    /**
     *
     * @param context
     * @param intervalMilliseconds
     */
    private void setAlarm(Context context, long intervalMilliseconds) {
        Log.d("AlarmManagerUtility", "setAlarmInterval " + (intervalMilliseconds / (1000 * 60)) + " min");
        cancelAlarm(context);
        Intent myIntent = new Intent(context, this.getClass());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 1, myIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + intervalMilliseconds, pendingIntent);
    }

}
