package com.example.rek.twoactivities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.rek.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;

    private TextView mTvReplyHeader;
    private TextView mTvReplyMessage;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvReplyHeader = findViewById(R.id.tvTextHeaderReply);
        mTvReplyMessage = findViewById(R.id.tvTextMessageReply);
        mEditText = findViewById(R.id.edtMain);

        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_message");
            // Return visibility to text views only if they were previously visible
            // Else use default layout
            if (isVisible) {
                mTvReplyHeader.setVisibility(View.VISIBLE);
                mTvReplyMessage.setVisibility(View.VISIBLE);
                mTvReplyMessage.setText(savedInstanceState.getString("reply_message"));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        if (mTvReplyHeader.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_message", true);
            outState.putString("reply_text", mTvReplyMessage.getText().toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST && resultCode == RESULT_OK) {
            String replyMessage = data.getStringExtra(SecondActivity.EXTRA_REPLY);

            mTvReplyHeader.setVisibility(View.VISIBLE);
            mTvReplyMessage.setVisibility(View.VISIBLE);
            mTvReplyMessage.setText(replyMessage);
        }
    }

    /**
     * Button callback function to launch second activity with message
     *
     * @param view  The button which called this function
     */
    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked");

        String message = mEditText.getText().toString();

        Intent intento = new Intent(this, SecondActivity.class);
        intento.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intento, TEXT_REQUEST);
    }

}
