package com.example.rek.standup;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    // Constants for notification
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    NotificationManager mNotificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        sendNotification(context);
    }

    /**
     * Send notification to phone
     * @param context
     */
    private void sendNotification(Context context) {
        // Explicit intent, returns to MainActivity
        Intent intento = new Intent(context, MainActivity.class);
        // Add to pending intent
        PendingIntent pintento = PendingIntent.getActivity(context, NOTIFICATION_ID, intento,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder buildo =
                new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_stand_up)
                        .setContentIntent(pintento)
                        .setContentTitle(context.getString(R.string.notification_title))
                        .setContentText(context.getString(R.string.notification_text))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL);

        mNotificationManager.notify(NOTIFICATION_ID, buildo.build());
    }
}
