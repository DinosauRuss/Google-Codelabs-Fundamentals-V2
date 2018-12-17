package com.example.rek.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.rek.twoactivities.extra.REPLY";

    private TextView mMessageText;
    private EditText mReplyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mMessageText = findViewById(R.id.tvTextMessage);
        mReplyEditText = findViewById(R.id.edtSecond);

        // Receive message from MainActivity
        Intent intento = getIntent();
        String message = intento.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mMessageText.setText(message);
    }

    /**
     * Button callback to send message back to MainActivity
     *
     * @param view  Button which called this function
     */
    public void returnReply(View view) {
        String reply = mReplyEditText.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
