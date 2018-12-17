package com.example.rek.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;


import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;

    public SimpleAsyncTask(TextView tv) {
        this.mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Generate random number between 5 and 10
        Random rando = new Random();
        int num = rando.nextInt(6) + 5;
        // Make the delay longer
        int delayTime = num * 200;

        // Sleep for previously generated length of time
        try {
            // Publish progress half way through delay
            Thread.sleep( delayTime/2 );
            publishProgress(delayTime);
            Thread.sleep( delayTime/2 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Send string to onPostExecute
        return "Awake at last after sleeping " + delayTime + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        String message = "Halfway done " + values[0] + " milliseconds";
        mTextView.get().setText(message);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        mTextView.get().setText(s);
    }
}
