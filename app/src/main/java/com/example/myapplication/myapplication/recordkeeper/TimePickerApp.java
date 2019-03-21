package com.example.myapplication.myapplication.recordkeeper;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class TimePickerApp{
    Button timeButton;
    Integer hour;
    Integer minute;
    public TimePickerApp(final FragmentManager supportFragmentManager, Button button, final int tag) {
        timeButton = button;
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = TimePickerFragment.newInstance(tag);
                timePicker.show(supportFragmentManager, "timePicker");
            }
        });

    }

    public Button getTimeButton() {
        return timeButton;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }
}
