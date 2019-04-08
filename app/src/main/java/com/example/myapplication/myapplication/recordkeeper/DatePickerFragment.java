package com.example.myapplication.myapplication.recordkeeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;


import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    DatePickedListener listener;
    Integer id;

    static DatePickerFragment newInstance(Integer id) {
        Bundle args = new Bundle();
        args.putInt("picker_id", id);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        id = getArguments().getInt("picker_id");

        return new DatePickerDialog(
                getActivity(), AlertDialog.THEME_HOLO_LIGHT, this,
                year, month, day);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Records the listener of the onDatePicked method
        try {
            listener = (DatePickedListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must utilise "
                                        + DatePickedListener.class.getName());
        }
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //Handles the date being set and triggers the callback to the listener
        if(listener != null) //If the listener exists
            listener.onDatePicked(year, month+1, day, id);
    }

    public interface DatePickedListener {
        void onDatePicked(int year, int month, int day, int id);
    }

}

