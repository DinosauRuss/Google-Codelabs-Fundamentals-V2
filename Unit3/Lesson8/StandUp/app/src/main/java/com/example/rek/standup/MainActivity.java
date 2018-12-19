package com.example.rek.standup;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // Constants for notification
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotificationManager;

    ToggleButton mTbAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toggle button
        mTbAlarm = findViewById(R.id.tbAlarm);

        // System managers
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        final AlarmManager mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Set up alarm intent
        Intent intento = new Intent(MainActivity.this, AlarmReceiver.class);
        boolean alarmUp = ( PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                intento, PendingIntent.FLAG_NO_CREATE) != null );
        mTbAlarm.setChecked(alarmUp);
        // Pending intent for alarm manager
        final PendingIntent pintento = PendingIntent.getBroadcast(this,
                NOTIFICATION_ID, intento, PendingIntent.FLAG_UPDATE_CURRENT);

        // Toggle button click listener
        mTbAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMessage;
                if (isChecked) {

                    long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                    long triggerTime = SystemClock.elapsedRealtime()
                            + repeatInterval;

                    // Create alarm to trigger 15 minutes from now
                    // and every 15 minutes repeated
                    if (mAlarmManager != null) {
                        mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                triggerTime, repeatInterval, pintento);
                    }

                    toastMessage = getResources().getString(R.string.alarm_on);
                } else {
                    // Cancel alarm
                    if (mAlarmManager != null) {
                        mAlarmManager.cancel(pintento);
                    }

                    // Cancel notification
                    mNotificationManager.cancelAll();
                    toastMessage = getResources().getString(R.string.alarm_off);
                }
                // Display toast with alarm state
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });

        createNotificationChannel();
    }

    /**
     * Create notification channel on API>26
     */
    private void createNotificationChannel() {
        // Check os version
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {

            // Create notification channel
            NotificationChannel channel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Stand Up Notification", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Stand up and walk every 15 minutes");

            // Add to manager
            mNotificationManager.createNotificationChannel(channel);
        }
    }

}
