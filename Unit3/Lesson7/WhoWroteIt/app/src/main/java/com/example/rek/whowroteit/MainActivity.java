package com.example.rek.whowroteit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtBookInput;
    private TextView mTvTitleText;
    private TextView mTvAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        mEdtBookInput = findViewById(R.id.edtBookInput);
        mTvTitleText = findViewById(R.id.tvTitleText);
        mTvAuthorText = findViewById(R.id.tvAuthorText);
    }

    public void searchBooks(View view) {
        // Get search string
        String searchText = mEdtBookInput.getText().toString();

        InputMethodManager inputManager =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Check network connection
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected() && searchText.length() != 0) {
            // Start AsyncTask
            new FetchBook(mTvTitleText, mTvAuthorText).execute(searchText);
            // Reset text views while AsyncTask is running
            mTvTitleText.setText(getString(R.string.loading));
            mTvAuthorText.setText("");
        } else {
            // Reset text views based on failure
            if (searchText.length() == 0) {
                mTvTitleText.setText(getString(R.string.no_search_entered));
                mTvAuthorText.setText("");
            } else {
                mTvTitleText.setText(getString(R.string.no_network_connection));
                mTvAuthorText.setText("");
            }
        }
    }

}
