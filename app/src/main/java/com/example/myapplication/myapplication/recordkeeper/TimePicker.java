package com.example.myapplication.myapplication.recordkeeper;

import android.app.TimePickerDialog;
import android.widget.Button;

public class TimePicker implements TimePickerDialog.OnTimeSetListener {
    Button timeButton;
    public TimePicker(Button button) {
        timeButton = button;

    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        timeButton.setText(String.format(hourOfDay + ":" + minute));
    }
}
