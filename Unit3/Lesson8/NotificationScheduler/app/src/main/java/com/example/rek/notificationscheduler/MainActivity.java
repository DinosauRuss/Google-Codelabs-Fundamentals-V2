package com.example.rek.notificationscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch mSwDeviceIdle;
    private Switch mSwDeviceCharging;
    private SeekBar mSkbDeadline;

    private JobScheduler mSchedulo;
    private final static int JOB_ID = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwDeviceIdle = findViewById(R.id.swIdle);
        mSwDeviceCharging = findViewById(R.id.swCharging);

        // Seekbar for job deadline
        final TextView seekBarProgress = findViewById(R.id.tvSeekBarStatus);
        mSkbDeadline = findViewById(R.id.skbDeadline);
        mSkbDeadline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 0) {
                    // Change label to match seekbar
                    String text = progress + "s";
                    seekBarProgress.setText(text);
                } else {
                    seekBarProgress.setText(getString(R.string.tv_seekbar_status_not_set));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void scheduleJob(View view) {
        // Which network type is selected
        RadioGroup newtorkOptionsGroup = findViewById(R.id.rgNetworkOptions);
        int selectedId = newtorkOptionsGroup.getCheckedRadioButtonId();

        int selectedOption = JobInfo.NETWORK_TYPE_NONE;
        switch (selectedId) {
            case R.id.rbNetworkNone:
                selectedOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.rbNetworkAny:
                selectedOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.rbNetworkWifi:
                selectedOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }

        mSchedulo = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        // Component name needed for JobInfo builder
        ComponentName serviceName = new ComponentName(getPackageName(),
                NotificationJobService.class.getName());
        // JobInfo builder
        JobInfo.Builder buildo = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(selectedOption)
                .setRequiresDeviceIdle(mSwDeviceIdle.isChecked())
                .setRequiresCharging(mSwDeviceCharging.isChecked());

        // Check if override deadline has been set
        int seekbarProgress = mSkbDeadline.getProgress();
        boolean deadlineSet = (seekbarProgress > 0);
        if (deadlineSet) {
            // Add override deadline to job
            buildo.setOverrideDeadline( seekbarProgress * 1000 );
        }

        // Confirm JobInfo has at least 1 constraint
        boolean constraintSet = ( selectedOption != JobInfo.NETWORK_TYPE_NONE )
                || mSwDeviceIdle.isChecked() || mSwDeviceCharging.isChecked()
                || deadlineSet;
        if (constraintSet) {
            // Schedule job
            mSchedulo.schedule(buildo.build());

            // Confirm job scheduled
            Toast.makeText(this, "Job Scheduled, will run when " +
                    "the constraints are met", Toast.LENGTH_SHORT).show();
        } else {
            mSchedulo = null;
            Toast.makeText(this, "Job Service has no constraints",
                    Toast.LENGTH_SHORT).show();
        }
            }

    public void cancelJobs(View view) {
        if (mSchedulo != null) {
            // Remove pending jobs
            mSchedulo.cancelAll();

            // Reset scheduler to null
            mSchedulo = null;

            // Confirm job canceled
            Toast.makeText(this, "Jobs Canceled", Toast.LENGTH_SHORT).show();

        }
    }
}
