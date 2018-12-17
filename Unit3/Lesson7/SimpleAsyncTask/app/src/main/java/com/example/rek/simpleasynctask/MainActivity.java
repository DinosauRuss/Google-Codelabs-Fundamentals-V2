package com.example.rek.simpleasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Key for saving state of text view
    private final String TEXT_STATE = "current_text";

    // Text view to display results
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tv1);

        // Restore previous state of text view
        if (savedInstanceState != null) {
            mTextView.setText( savedInstanceState.getString(TEXT_STATE) );
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the state of the text view
        outState.putString( TEXT_STATE, mTextView.getText().toString() );
    }

    public void startTask(View view) {
        // Display temp message in text view
        mTextView.setText(getString(R.string.napping));

        // Start a new Asyncask
        new SimpleAsyncTask(mTextView).execute();
        }

}
