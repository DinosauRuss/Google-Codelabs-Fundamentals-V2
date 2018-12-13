package com.example.rek.hellocompat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTvHello;
    private final String COLOR_KEY = "color";

    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvHello = findViewById(R.id.tvHello);

        if (savedInstanceState != null) {
            mTvHello.setTextColor(savedInstanceState.getInt(COLOR_KEY));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        int color = mTvHello.getCurrentTextColor();
        outState.putInt(COLOR_KEY, color);

        super.onSaveInstanceState(outState);
    }

    public void changeColor(View view) {
        Random rando  = new Random();
        String colorName = mColorArray[rando.nextInt(mColorArray.length)];
        int colorResourceName = getResources().getIdentifier(colorName, "color",
                getApplicationContext().getPackageName());
        int colorRes = ContextCompat.getColor(this, colorResourceName);
        mTvHello.setTextColor(colorRes);
    }
}
