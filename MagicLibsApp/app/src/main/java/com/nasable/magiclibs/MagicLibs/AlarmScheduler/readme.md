 # Alarm Scheduler
 
- Extend this class and implement the methods
 
## Example: MyAlarm
 
 ```
 MyAlarm extends AlarmManagerUtility{

@Override public long getInterval(){
return  AlarmManager.INTERVAL_FIFTEEN_MINUTES;
}

@Override public void action(){
Log.d("MyAlarm","Alarm has been triggered");
}

}
```

- Don't forget to register the receiver in the manifest.xml
 
```
 <receiver
 android:name=".MyAlarm"
 android:enabled="true"
 android:exported="true">
     <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED" />
     </intent-filter>
 </receiver>
 
```

## to set an alarm just call:
  
```new MyAlarm().setAlarm(getContext());```

