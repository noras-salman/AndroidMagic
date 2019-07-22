package com.nasable.magiclibs.MagicLibs.NotificationTriggerer;

import android.content.Context;

import com.nasable.magiclibs.R;

public class NotificationExample {

    public void newsNotification(Context context,String title,String content){

        // What type of notification/channel
        NotificationManagerUtility.NotificationChannelInfo notificationChannelInfo=new NotificationManagerUtility.NotificationChannelInfo("01","News Notifications","Notifications about the latest news");

        //Build the content
        NotificationManagerUtility.NotificationInfo notificationInfo= new NotificationManagerUtility.NotificationInfo(title,content, R.mipmap.ic_launcher);

        //create instance
        NotificationManagerUtility notificationManagerUtility=  new NotificationManagerUtility(context,notificationChannelInfo ,notificationInfo );

        //trigger
        notificationManagerUtility.triggerNotification();
    }
}
