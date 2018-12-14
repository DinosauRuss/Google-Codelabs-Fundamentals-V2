package com.example.rek.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Vars to hold scores
    private int mScore1;
    private int mScore2;

    static final String SCORE1_STATE = "Team1 Score";
    static final String SCORE2_STATE = "Team2 Scroe";

    private TextView mTvScoreTeam1;
    private TextView mTvScoreTeam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvScoreTeam1 = findViewById(R.id.tvScoreTeam1);
        mTvScoreTeam2 = findViewById(R.id.tvScoreTeam2);

        if (savedInstanceState != null) {
            // Get prev scores
            mScore1 = savedInstanceState.getInt(SCORE1_STATE);
            mScore2 = savedInstanceState.getInt(SCORE2_STATE);
            // Reset text views
            mTvScoreTeam1.setText(String.valueOf(mScore1));
            mTvScoreTeam2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Change label
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.menu_night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.menu_night_mode).setTitle(R.string.night_mode);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_night_mode) {
            // Get current night mode of app
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            // Toggle night mode
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }


            // Re-create activity for theme to take effect
            recreate();
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the current scores
        outState.putInt(SCORE1_STATE, mScore1);
        outState.putInt(SCORE2_STATE, mScore2);

        super.onSaveInstanceState(outState);
    }

    /**
     * Handle both increment buttons
     * @param view  Button which was clicked
     */
    public void increaseScore(View view) {
        // Id of image which was clicked
        int id = view.getId();

        switch (id) {
            case R.id.ibIncreaseTeam1:
                mScore1++;
                mTvScoreTeam1.setText(String.valueOf(mScore1));
                break;
            case R.id.ibIncreaseTeam2:
                mScore2++;
                mTvScoreTeam2.setText(String.valueOf(mScore2));
                break;
        }
    }

    /**
     * Handle both decrement buttons
     * @param view  Button which was clicked
     */
    public void decreaseScore(View view) {
        // Id of image which was clicked
        int id = view.getId();

        switch (id) {
            case R.id.ibDecreaseTeam1:
                mScore1--;
                mTvScoreTeam1.setText(String.valueOf(mScore1));
                break;
            case R.id.ibDecreaseTeam2:
                mScore2--;
                mTvScoreTeam2.setText(String.valueOf(mScore2));
                break;
        }
    }

}
