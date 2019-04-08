package com.example.myapplication.myapplication.recordkeeper;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

class TimePickerApp{
    private Button timeButton;
    private Integer hour;
    private Integer minute;
    TimePickerApp(final FragmentManager supportFragmentManager,
                  Button button, final int tag, final boolean time) {
        timeButton = button;
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = TimePickerFragment.newInstance(tag, time);
                timePicker.show(supportFragmentManager, "timePicker");
            }
        });
    }

    Button getTimeButton() {
        return timeButton;
    }

    Integer getHour() {
        return hour;
    }

    void setHour(Integer hour) {
        this.hour = hour;
    }

    Integer getMinute() {
        return minute;
    }

    void setMinute(Integer minute) {
        this.minute = minute;
    }
}
