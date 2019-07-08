package com.nasable.adapter.AlarmScheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/***
 * * * * * * * * * * * * * * * * * * * * * * *
 * Extend this class and implement the methods
 * * * * * * * * * * * * * * * * * * * * * * *
 * Example: MyAlarm
 MyAlarm extends AlarmManagerUtility{

    @Override
    public long getInterval(){
        return  AlarmManager.INTERVAL_FIFTEEN_MINUTES;
    }

    @Override
    public void action(){
        Log.d("MyAlarm","Alarm has been triggered");
    }

 }
 * * * * * * * * * * * * * * * * * * * * * * *
 * to set an alarm just call // new MyAlarm().setAlarm(getContext());
 * * * * * * * * * * * * * * * * * * * * * * *
 * Don't forget to register the receiver in the manifest.xml

 <receiver
 android:name=".MyAlarm"
 android:enabled="true"
 android:exported="true">
     <intent-filter>
         <action android:name="android.intent.action.BOOT_COMPLETED" />
     </intent-filter>
 </receiver>
 * * * * * * * * * * * * * * * * * * * * * * *
 * ***/

public abstract  class AlarmManagerUtility extends AlarmBroadcastReceiver {

    @Override
    public void whenDone(Context context) {
        setAlarm(context,getInterval());
    }

    public void setAlarm(Context context){
        setAlarm(context,getInterval());
    }

    private  void setAlarm(Context context, long intervalFromNow){
        Log.d("AlarmManagerUtility","setAlarm interval "+(intervalFromNow/(1000*60)) +" min");
        cancelAlarm(context);
        Intent myIntent = new Intent(context, this.getClass());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 1, myIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+intervalFromNow, pendingIntent);
    }

    public  void cancelAlarm(Context context){
        Log.d("AlarmManagerUtility","cancelAlarm");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(context, this.getClass());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 1, myIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

}
