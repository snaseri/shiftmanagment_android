package com.example.myapplication.myapplication.recordkeeper;

import android.app.Activity;
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

        DatePickerDialog dateSetter = new DatePickerDialog(
                getActivity(),
                this,
                year, month, day);
        return dateSetter;
    }
    @Override
    public void onAttach(Activity activity) {
        // when the fragment is initially shown (i.e. attached to the activity),
        // cast the activity to the callback interface type
        super.onAttach(activity);
        try {
            listener = (DatePickedListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + DatePickedListener.class.getName());
        }
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // when the time is selected, send it to the activity via its callback
        // interface method

        if(listener != null)
            listener.onDatePicked(year, month, day, id);
    }

    public static interface DatePickerDialogListener {
        public void onDateSet(DatePicker view, int year, int month, int day);
    }

    public static interface DatePickedListener {
        public void onDatePicked(int year, int month, int day, int id);
    }

}

