package com.kevinandrianasolo.m1p9android.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.kevinandrianasolo.m1p9android.R;

public class NotificationUtils {
    private static NotificationUtils instance;
    private int notificationId = 0;
    private String CHANNEL_ID = "Ludikids Channel";
    private NotificationUtils(){

    }
    public static NotificationUtils getInstance(){
        if(instance==null){
            instance = new NotificationUtils();
        }
        return instance;
    }

    public Boolean isNotificationActivated(Context context){
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        SharedPreferences sharedPref = sharedPreferencesUtils.getSharedPreferences();
        Boolean isNotificationActivated = sharedPref.getBoolean(context.getString(R.string.preferences_notifications), true);
        return isNotificationActivated;
    }

    public void showBasicNotification(Context context, String textTitle, String textContent){
        if(!this.isNotificationActivated(context)) {
            Toast.makeText(context, "Pour voir les notifications: Paramètres > 'Activer les notifications'", Toast.LENGTH_LONG).show();
            return;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder.build());
        notificationId ++; // Increment notificationId if notification is sent
    }


    public void initNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
