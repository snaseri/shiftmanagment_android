package com.example.myapplication.myapplication.recordkeeper;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;


import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    TimePickedListener listener;
    Integer id;

    static TimePickerFragment newInstance(Integer id) {
        Bundle args = new Bundle();
        args.putInt("picker_id", id);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        id = getArguments().getInt("picker_id");

        TimePickerDialog timeSetter = new TimePickerDialog(
                getActivity(),
                this,
                hour, min, DateFormat.is24HourFormat(getActivity()));
        return timeSetter;
    }
    @Override
    public void onAttach(Activity activity) {
        // when the fragment is initially shown (i.e. attached to the activity),
        // cast the activity to the callback interface type
        super.onAttach(activity);
        try {
            listener = (TimePickedListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + TimePickedListener.class.getName());
        }
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // when the time is selected, send it to the activity via its callback
        // interface method

        if(listener != null)
            listener.onTimePicked(hourOfDay, minute, id);
    }

    public static interface TimePickerDialogListener {
        public void onTimeSet(int id, TimePicker view, int hourOfDay, int minute);
    }

    public static interface TimePickedListener {
        public void onTimePicked(int hourOfDay, int minute, int id);
    }

}
