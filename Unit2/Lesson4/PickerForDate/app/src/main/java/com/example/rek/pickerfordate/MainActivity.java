package com.example.rek.pickerfordate;

import android.support.v4.app.DialogFragment;
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

    /**
     * Button callback to display DatePickerFragment
     * @param view  Button which called this function
     */
    public void showDatePicker(View view) {
        DialogFragment diago = new DatePickerFragment();
        diago.show(getSupportFragmentManager(), getString(R.string.datepicker));
    }

    /**
     * Process data returned from DatePickerFragment
     * @param year  Year of selected date
     * @param month Month of selected date
     * @param day   Day of selected date
     */
    public void processDatePickerResult(int year, int month, int day) {
        // Process date values into string
        String strMonth = Integer.toString(month + 1);
        String strDay = Integer.toString(day);
        String strYear = Integer.toString(year);
        String date = ( strMonth + "/" + strDay + "/" + strYear );
        String message = "Date: " + date;
        // Display toast of the date string
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showTimePicker(View view) {
        DialogFragment diago = new TimePickerFragment();
        diago.show(getSupportFragmentManager(), getString(R.string.timepicker));
    }

    public void processTimePickerResult(int hour, int minute) {
        // Process data into string
        String strHour = Integer.toString(hour);
        String strMinute = Integer.toString(minute);
        String time = ( strHour + ":" + strMinute );
        String message = "Time: " + time;
        // Display toast with message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
