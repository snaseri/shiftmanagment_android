package com.example.myapplication.myapplication.recordkeeper;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class DatePickerApp {
    Button dateButton;
    Integer year;
    Integer month;
    Integer day;
    public DatePickerApp(final FragmentManager supportFragmentManager, Button button, final int tag) {
        dateButton = button;
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = DatePickerFragment.newInstance(tag);
                datePicker.show(supportFragmentManager, "Select Start Time");

            }
        });

    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public Button getDateButton() {
        return dateButton;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
