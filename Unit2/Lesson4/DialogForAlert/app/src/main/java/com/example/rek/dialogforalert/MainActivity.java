package com.example.rek.dialogforalert;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAlert(View view) {
        AlertDialog.Builder mAlertBuilder = new AlertDialog.Builder(this);

        // Set title and message
        mAlertBuilder.setTitle("Alert!");
        mAlertBuilder.setMessage("Click OK to continue, or Cancel to stop:");

        // Add buttons
        mAlertBuilder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked OK button
                Toast.makeText(getApplicationContext(), getString(R.string.msg_ok), Toast.LENGTH_SHORT).show();
            }
        });
        mAlertBuilder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User pressed Cancel
                Toast.makeText(getApplicationContext(), getString(R.string.msg_cancel), Toast.LENGTH_SHORT).show();
            }
        });

        // Show the dialog
        mAlertBuilder.show();
    }
}
