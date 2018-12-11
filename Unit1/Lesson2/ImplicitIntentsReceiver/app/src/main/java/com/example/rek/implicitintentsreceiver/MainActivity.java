package com.example.rek.implicitintentsreceiver;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rek.implicitintentsreceiver.R;

public class MainActivity extends AppCompatActivity {

    TextView mTvUriMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvUriMessage = findViewById(R.id.tvUriMessage);

        Intent intento = getIntent();
        Uri uri = intento.getData();
        if (uri != null) {
            String uriString = getString(R.string.uri_label) + uri.toString();
            mTvUriMessage.setText(uriString);
        }
    }
}
