package com.example.myapplication.myapplication.recordkeeper;

import android.app.Activity;
import android.app.AlertDialog;
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
    Boolean time;

    static TimePickerFragment newInstance(Integer id, Boolean time) {
        Bundle args = new Bundle();
        args.putInt("picker_id", id);
        args.putBoolean("picker_time", time);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int hour = 0;
        int min = 0;

        id = getArguments().getInt("picker_id");
        if (getArguments().getBoolean("picker_time")){
            Calendar cal = Calendar.getInstance();
            hour = cal.get(Calendar.HOUR_OF_DAY);
            min = cal.get(Calendar.MINUTE);

        }

        return new TimePickerDialog(
                getActivity(), AlertDialog.THEME_HOLO_LIGHT, this,
                hour, min,true);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Records the listener of the onTimePicked method
        try {
            listener = (TimePickedListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must utilise "
                                        + TimePickedListener.class.getName());
        }
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Handles the date being set and triggers the callback to the listener
        if(listener != null) //If the listener exists
            listener.onTimePicked(hourOfDay, minute, id);
    }

    public interface TimePickedListener {
        void onTimePicked(int hourOfDay, int minute, int id);
    }

}
