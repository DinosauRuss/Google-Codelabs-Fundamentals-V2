package com.example.rek.pickerfordate;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Current date is default for date picker
        final Calendar calendo = Calendar.getInstance();
        int year = calendo.get(Calendar.YEAR);
        int month = calendo.get(Calendar.MONTH);
        int day = calendo.get(Calendar.DAY_OF_MONTH);

        // Return new date picker dialog with defaults
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Activity the fragment is currently associated with
        MainActivity acto = (MainActivity) getActivity();
        acto.processDatePickerResult(year, month, dayOfMonth);
    }
}
