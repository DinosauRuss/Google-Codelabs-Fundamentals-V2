package com.example.rek.pickerfordate;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Current time is default
        Calendar calendo = Calendar.getInstance();
        int hour = calendo.get(Calendar.HOUR_OF_DAY);
        int minute = calendo.get(Calendar.MINUTE);

        // Return new time picker dialog with defaults
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        // Activity fragment is currently associated with
        MainActivity acto = (MainActivity) getActivity();
        acto.processTimePickerResult(hourOfDay, minute);
    }
}
