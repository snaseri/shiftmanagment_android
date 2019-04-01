package com.example.myapplication.myapplication.recordkeeper;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

class DatePickerApp {
    private Button dateButton;
    private Integer year;
    private Integer month;
    private Integer day;
    DatePickerApp(final FragmentManager supportFragmentManager, Button button, final int tag) {
        dateButton = button;
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = DatePickerFragment.newInstance(tag);
                datePicker.show(supportFragmentManager, "Select Start Time");
            }
        });
    }

    Button getDateButton() {
        return dateButton;
    }

    Integer getYear() {
        return year;
    }

    void setYear(Integer year) {
        this.year = year;
    }

    Integer getMonth() {
        return month;
    }

    void setMonth(Integer month) {
        this.month = month;
    }

    Integer getDay() {
        return day;
    }

    void setDay(Integer day) {
        this.day = day;
    }
}
