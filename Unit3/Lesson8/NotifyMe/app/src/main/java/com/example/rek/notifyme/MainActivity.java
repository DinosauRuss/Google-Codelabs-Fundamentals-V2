package com.example.rek.notifyme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    // Action for broadcast
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";

    private NotificationReceiver mNotificationReceiver;

    // Unique identifier for notification channel
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;

    // Id for the notification
    private static final int NOTIFICATION_ID = 0;

    private Button mBtnNotify;
    private Button mBtnUpdate;
    private Button mBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons with click listeners
        mBtnNotify = findViewById(R.id.btnNotify);
        mBtnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
        mBtnUpdate = findViewById(R.id.btnUpdate);
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();
            }
        });
        mBtnCancel = findViewById(R.id.btnCancel);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNotification();
            }
        });

        mNotificationReceiver = new NotificationReceiver();
        this.registerReceiver(mNotificationReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));

        createNotificationChannel();

        toggleButtonStates(true, false, false);
    }

    public void createNotificationChannel(){
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // If android device is Oreo(26) or higher
        if ( Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ) {
            // Create notification channel
            NotificationChannel notifyChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);
            notifyChannel.enableLights(true);
            notifyChannel.setLightColor(R.color.orangish);
            notifyChannel.enableVibration(true);
            notifyChannel.setDescription("Enable Notifications from Mascot");

            mNotifyManager.createNotificationChannel(notifyChannel);
        }
    }

    public void sendNotification() {
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent pIntento = PendingIntent.getBroadcast(
                this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder buildo = getNotificationBuilder();
        buildo.addAction(R.drawable.ic_update, "Update Notification", pIntento);

        mNotifyManager.notify(NOTIFICATION_ID, buildo.build());

        toggleButtonStates(false, true, true);

    }

    private NotificationCompat.Builder getNotificationBuilder() {
        // Return to MainActivity when notification is pressed
        Intent intento = new Intent(this, MainActivity.class);
        // Wrap with PendingIntent, pass into builder
        PendingIntent notificationIntent = PendingIntent.getActivity(
                this, NOTIFICATION_ID, intento, PendingIntent.FLAG_UPDATE_CURRENT);

        // Notification builder
        NotificationCompat.Builder buildo = new NotificationCompat.Builder(
                this, PRIMARY_CHANNEL_ID);
        buildo.setContentTitle("You have been notified")
                .setContentText("This is your notification text")
                .setSmallIcon(R.drawable.ic_droid)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(notificationIntent)
                .setAutoCancel(true);

        return buildo;
    }

    private void updateNotification() {
        // Convert image to bitmap
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);

        NotificationCompat.Builder buildo = getNotificationBuilder();
        buildo.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(image)
                .setBigContentTitle("Notification Updated"));
        mNotifyManager.notify(NOTIFICATION_ID, buildo.build());

        toggleButtonStates(false, false, true);
    }

    private void cancelNotification() {
        mNotifyManager.cancel(NOTIFICATION_ID);

        toggleButtonStates(true, false, false);
    }

    private void toggleButtonStates(Boolean notifyState, Boolean updateState,
                                    Boolean cancelState) {
        mBtnNotify.setEnabled(notifyState);
        mBtnUpdate.setEnabled(updateState);
        mBtnCancel.setEnabled(cancelState);
    }


    public class NotificationReceiver extends BroadcastReceiver {

        NotificationReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification();
        }
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mNotificationReceiver);
        super.onDestroy();
    }
}
