package com.nasable.magiclibs.MagicLibs.NotificationTriggerer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresPermission;
import androidx.core.app.NotificationCompat;

import static android.Manifest.permission.FOREGROUND_SERVICE;
import static androidx.core.app.NotificationCompat.PRIORITY_LOW;

public class NotificationManagerUtility {

    private Context context;
    private NotificationChannelInfo notificationChannelInfo;
    private NotificationInfo notificationInfo;

    /**
     * @param context
     * @param notificationChannelInfo
     * @param notificationInfo
     */
    public NotificationManagerUtility(Context context, NotificationChannelInfo notificationChannelInfo, NotificationInfo notificationInfo) {
        this.context = context;
        this.notificationChannelInfo = notificationChannelInfo;
        this.notificationInfo = notificationInfo;
    }

    /**
     * @return
     */
    public NotificationChannelInfo getNotificationChannelInfo() {
        return notificationChannelInfo;
    }

    /**
     * @return
     */
    public NotificationInfo getNotificationInfo() {
        return notificationInfo;
    }

    /**
     * @return
     */
    private NotificationManager getPreparedNotificationManager() {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(notificationChannelInfo.getChannelId(), notificationChannelInfo.getChannelId(), notificationChannelInfo.getImportance());
            mChannel.setDescription(notificationChannelInfo.getChannelDescription());
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        return notificationManager;
    }


    /**
     * @return a notification ready to be triggered
     */
    private Notification getNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, notificationChannelInfo.getChannelId())
                .setSmallIcon(notificationInfo.getNotificationIcon())
                .setContentTitle(notificationInfo.getContentTitle())
                .setContentText(notificationInfo.getContentText());


        builder.setOngoing(notificationInfo.isOnGoing());

        if (notificationInfo.getResultIntent() != null) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntent(notificationInfo.getResultIntent());

            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(resultPendingIntent);
        }

        return builder.build();
    }

    /**
     *
     */
    public void triggerNotification() {
        NotificationManager notificationManager = getPreparedNotificationManager();
        Notification notification = getNotification();
        notificationManager.notify(notificationInfo.getNotificationId(), notification);

    }

    /**
     *
     * @param service
     * @param channel
     * @param notificationInfo
     */
    @RequiresPermission(FOREGROUND_SERVICE)
    public static void startForegroundService(Service service, NotificationChannelInfo channel, NotificationInfo notificationInfo) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) service.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

            String name = channel.getChannelTitle();
            int importance = NotificationManager.IMPORTANCE_LOW;

            NotificationChannel mChannel = new NotificationChannel(channel.getChannelId(), name, importance);

            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(mChannel);
            }
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(service.getApplicationContext(), channel.getChannelId()).setSmallIcon(notificationInfo.getNotificationIcon()).setContentTitle(notificationInfo.getContentTitle());
        Notification notification = mBuilder
                .setPriority(PRIORITY_LOW)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();

        service.startForeground(notificationInfo.getNotificationId(), notification);
    }


    /**
     *
     */
    public static class NotificationChannelInfo {
        private String channelId;
        private String channelTitle;
        private String channelDescription;
        private int importance = NotificationManager.IMPORTANCE_HIGH;

        /**
         * @param channelId
         * @param channelTitle
         * @param channelDescription
         */
        public NotificationChannelInfo(String channelId, String channelTitle, String channelDescription) {
            this.channelId = channelId;
            this.channelTitle = channelTitle;
            this.channelDescription = channelDescription;
        }

        public String getChannelId() {
            return channelId;
        }

        public String getChannelTitle() {
            return channelTitle;
        }

        public String getChannelDescription() {
            return channelDescription;
        }

        public int getImportance() {
            return importance;
        }

        /**
         * @param importance
         */
        public void setImportance(int importance) {
            this.importance = importance;
        }
    }


    public static class NotificationInfo {
        private int notificationId = 0x01;
        private String contentTitle;
        private String contentText;
        private int notificationIcon;
        private Intent resultIntent;
        private boolean onGoing = true;

        /**
         * @param contentTitle
         * @param contentText
         * @param notificationIcon
         */
        public NotificationInfo(String contentTitle, String contentText, int notificationIcon) {
            this.contentTitle = contentTitle;
            this.contentText = contentText;
            this.notificationIcon = notificationIcon;

        }

        /**
         * @param resultIntent
         */
        public void setResultIntent(Intent resultIntent) {
            this.resultIntent = resultIntent;
        }

        /**
         * @param notificationId
         */
        public void setNotificationId(int notificationId) {
            this.notificationId = notificationId;
        }

        /**
         * @param onGoing
         */
        public void setOnGoing(boolean onGoing) {
            this.onGoing = onGoing;
        }

        public boolean isOnGoing() {
            return onGoing;
        }

        public int getNotificationId() {
            return notificationId;
        }

        public String getContentTitle() {
            return contentTitle;
        }

        public String getContentText() {
            return contentText;
        }

        public int getNotificationIcon() {
            return notificationIcon;
        }

        public Intent getResultIntent() {
            return resultIntent;
        }
    }


}
