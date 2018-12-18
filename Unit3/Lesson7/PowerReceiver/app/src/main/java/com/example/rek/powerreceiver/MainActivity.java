package com.example.rek.powerreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Action string for intent broadcast
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    private CustomReceiver mCusto = new CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Filter types of Intents to receive
        IntentFilter filto = new IntentFilter();
        filto.addAction(Intent.ACTION_POWER_CONNECTED);
        filto.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filto.addAction(Intent.ACTION_HEADSET_PLUG);

        // Receive filtered broadcasts
        // as long as this Activity is active
        this.registerReceiver(mCusto, filto);

        // Register this Activity to receive custom local broadcasts
        LocalBroadcastManager.getInstance(this).registerReceiver(mCusto,
                new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    @Override
    protected void onDestroy() {
        // Unregister broadcast receiver
        this.unregisterReceiver(mCusto);

        // Unregister local broadcast receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mCusto);

        super.onDestroy();
    }

    public void sendCustomBroadcast(View view) {
        // Send custom broadcast locally within this app
        Intent intento = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intento);
    }
}
