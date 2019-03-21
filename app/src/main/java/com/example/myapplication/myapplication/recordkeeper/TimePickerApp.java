package com.example.myapplication.myapplication.recordkeeper;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class TimePickerApp{
    Button timeButton;
    public TimePickerApp(final FragmentManager supportFragmentManager, Button button) {
        timeButton = button;
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(supportFragmentManager, "Select Start Time");

            }
        });

    }

}
