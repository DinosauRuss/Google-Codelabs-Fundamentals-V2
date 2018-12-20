package com.example.android.hellosharedprefs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private Spinner mSpnColorChooser;
    private int mColor;

    // Key for current color
    private final String COLOR_KEY = "color";

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.hellosharedprefs";

    // For logging
    private final static String TAG = "something";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Spinner with listener
        mSpnColorChooser = findViewById(R.id.spnColorChooser);
        mSpnColorChooser.setOnItemSelectedListener(this);
        // Adapter for spinner
        ArrayAdapter<CharSequence> adapto = ArrayAdapter.createFromResource(this,
                R.array.colors, android.R.layout.simple_spinner_item);
        // Dropdown style
        adapto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Attach adapter to spinner
        mSpnColorChooser.setAdapter(adapto);
    }

    public void returnToMain() {
        // Return to previous instance of MainActivity
        Intent intento = new Intent(this, MainActivity.class);
        intento.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intento);
    }

    public void cancelFunc(View view) {
        returnToMain();
    }

    public void saveColor(View view) {
        // Open shared preferences file
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        // Edit shared preferences
        SharedPreferences.Editor edito = mPreferences.edit();
        edito.putInt(COLOR_KEY, mColor);
        edito.apply();

        returnToMain();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // Text from spinner
        String item = (adapterView.getItemAtPosition(i).toString()
                + "_background").toLowerCase();
        // Get color resource id from name
        int id = getResources().getIdentifier(item, "color", this.getPackageName());
        // Get color using the id
        mColor = ContextCompat.getColor(getApplicationContext(), id);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

}
