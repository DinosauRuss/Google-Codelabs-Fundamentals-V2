package com.example.rek.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    private TextView mTvOrderMessage;

    private String mReceiveMessage;
    private final String ORDER_MESSAGE = "order_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Receive intent and message from MainActivity
        Intent intento = getIntent();
        mReceiveMessage = intento.getStringExtra(ORDER_MESSAGE);

        // Change text based on message from MainActivity
        mTvOrderMessage = findViewById(R.id.tvOrderMessage);
        mTvOrderMessage.setText(mReceiveMessage);
    }

}
