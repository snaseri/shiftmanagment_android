package com.example.myapplication.myapplication.recordkeeper;

import android.app.DatePickerDialog;
import android.widget.Button;

public class DatePicker implements DatePickerDialog.OnDateSetListener {
    Button dateButton;
    public DatePicker(Button button) {
        dateButton = button;

    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        dateButton.setText(String.format(dayOfMonth + "/" + month + "/" + year));
    }
}
