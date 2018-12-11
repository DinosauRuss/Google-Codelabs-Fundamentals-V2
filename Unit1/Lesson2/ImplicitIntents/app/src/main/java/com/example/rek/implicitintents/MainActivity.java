package com.example.rek.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextWeb;
    private EditText mEditTextLoc;
    private EditText mEditTextShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextWeb = findViewById(R.id.edtWebsite);
        mEditTextLoc = findViewById(R.id.edtLocation);
        mEditTextShare = findViewById(R.id.edtShare);
    }

    /**
     * Callback function to open website of provided url
     *
     * @param view  The button which called this function
     */
    public void openWebsite(View view) {
        // Get URL text
        String url = mEditTextWeb.getText().toString();

        // Parse the url into and create intent
        Uri webpage = Uri.parse(url);
        Intent intento = new Intent(Intent.ACTION_VIEW, webpage);

        // Find an activity to send the Intent, start that activity
        if (intento.resolveActivity(getPackageManager()) != null) {
            startActivity(intento);
        } else {
            Log.d("ImplicitIntents", "Cannot handle this");
        }
    }

    /**
     * Callback function to open provided location
     *
     * @param view  Button which called this function
     */
    public void openLocation(View view) {
        // Get location from edit text
        String locationText = mEditTextLoc.getText().toString();

        // Parse location and create intent
        Uri locationUri = Uri.parse("geo:0,0?q=" + locationText);
        Intent intento = new Intent(Intent.ACTION_VIEW, locationUri);

        // Find activity to send the Intent, start that activity
        if (intento.resolveActivity(getPackageManager()) != null) {
            startActivity(intento);
        } else {
            Log.d("ImplicitIntents", "Cannot handle this");
        }
    }


    public void shareText(View view) {
        // Get text to share from edit text
        String text = mEditTextShare.getText().toString();

        // Build intent and launch chooser
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_app_chooser)
                .setText(text)
                .startChooser();
    }

}

