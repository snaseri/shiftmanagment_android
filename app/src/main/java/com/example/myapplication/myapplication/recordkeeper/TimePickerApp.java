package com.example.myapplication.myapplication.recordkeeper;

import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class TimePickerApp implements TimePickerDialog.OnTimeSetListener {
    Button timeButton;
    Integer hour;
    Integer min;
    public TimePickerApp(final FragmentManager manager, Button button) {
        timeButton = button;
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(manager, "Select Start Time");

            }
        });

    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        timeButton.setText(String.format(hourOfDay + ":" + minute));
        setHour(hourOfDay);
        setMinute(minute);
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return min;
    }

    public void setMinute(Integer min) {
        this.min = min;
    }
}
