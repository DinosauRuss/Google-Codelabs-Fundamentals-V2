package com.example.rek.notificationscheduler;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class TestTask extends AsyncTask<Void, Void, Void> {

    private boolean mSuccess;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(5000);
            mSuccess = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            mSuccess = false;
        }
        return null;
    }

}
