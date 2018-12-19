package com.example.rek.notificationscheduler;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


public class NotificationJobService extends JobService {

    private NotificationManager mNotificationManager;

    SleepTask sleepo;
    private JobParameters mParams;

    // Notification channel IDs
    private final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private final int notificationID = 44;

    @Override
    public boolean onStartJob(JobParameters params) {
        this.mParams = params;

//        // Make a notification
//        createNotificationChannel();
//        // Pending intent sends user back to MainActivity
//        Intent intento = new Intent(this, MainActivity.class);
//        PendingIntent pintento = PendingIntent.getActivity(this, 0,
//                intento, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder buildo = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
//                .setContentTitle(getString(R.string.jobService_title))
//                .setContentText(getString(R.string.jobService_text))
//                .setContentIntent(pintento)
//                .setSmallIcon(R.drawable.ic_job_running)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                .setAutoCancel(true);
//
//        mNotificationManager.notify(notificationID, buildo.build());

        // AsyncTask to sleep for 5 sec
        sleepo = new SleepTask();
        sleepo.execute();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        // Stop AsyncTask
        if (sleepo != null) {
            sleepo.cancel(true);

            Toast.makeText(this, "Job Failed", Toast.LENGTH_SHORT).show();
        }
        // Reschedule job if it fails
        return true;
    }

    /**
     * Create notification channel for Oreo and higher
     */
    private void createNotificationChannel() {
        // Initialize notification manager
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // For Oreo and higher
        if ( Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ) {

            NotificationChannel channel = new NotificationChannel(
                    PRIMARY_CHANNEL_ID, getString(R.string.notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH );
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setDescription(getString(R.string.notification_channel_desc));

            mNotificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * AsyncTask class sleeps for 5 seconds
     */
    private class SleepTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(),
                    "Job Started", Toast.LENGTH_SHORT).show();

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Finish job from job scheduler
            jobFinished(mParams, false);

            Toast.makeText(getApplicationContext(), "Job Finished", Toast.LENGTH_SHORT).show();

            super.onPostExecute(aVoid);
        }
    }

}
