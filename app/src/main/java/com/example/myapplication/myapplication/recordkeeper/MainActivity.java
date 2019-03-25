package com.example.myapplication.myapplication.recordkeeper;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.myapplication.recordkeeper.database.*;
import com.example.myapplication.myapplication.recordkeeper.views.ShiftlogListItemView;

import java.util.List;

// DateTime interface includes both custom Listeners for TimePicker and DatePicker
interface DateTime extends TimePickerFragment.TimePickedListener,
        DatePickerFragment.DatePickedListener{}

public class MainActivity extends AppCompatActivity
        implements DateTime, PastShiftLogsFragment.OnListFragmentInteractionListener {

    private AppCompatEditText name;
    private AppCompatEditText company;
    private AppCompatEditText agency;
    private AppCompatButton saveButton;

    //Time pickers
    private TimePickerApp startTimePicker;
    private TimePickerApp endTimePicker;

    //Date pickers
    private DatePickerApp startDatePicker;
    private DatePickerApp endDatePicker;

    //Detail checkboxes
    private CheckBox vehicleUse;
    private CheckBox nightOut;

    //Strings of the selected DateTimes
    private String startDate; private String startTime;
    private String endDate; private String endTime;

    private static ShiftlogDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = (findViewById(R.id.nameInput));
        company = (findViewById(R.id.companyInput));
        agency = (findViewById(R.id.AgencyInput));
        saveButton = findViewById(R.id.Save_Button);

        //Time pickers
        startTimePicker = new TimePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnStartTimePicker), 0);
        endTimePicker = new TimePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnEndTimePicker), 1);

        //Date pickers
        startDatePicker = new DatePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnStartDatePicker), 0);
        endDatePicker = new DatePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnEndDatePicker), 1);

        //Detail checkboxes
        vehicleUse = (findViewById(R.id.vehicleUse));
        nightOut = (findViewById(R.id.nightOut));


        db = Room.databaseBuilder(this, ShiftlogDatabase.class,
                "ShiftlogDatabase").build().shiftlogDAO();


        //Checkbox click listeners
        vehicleUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    Toast.makeText(MainActivity.this,
                            "Vehicle operating selected",Toast.LENGTH_SHORT).show();
                }
            }
        });

        nightOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //Save button Listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override  //setting what happens when clicked below
            public void onClick(View v) {

            if (validateTimes()) {

            AsyncTask.execute(new Runnable() {


                @Override
                public void run() {

                    db.insertShiftlog(
                            new Shiftlog(name.getText().toString(), company.getText().toString(), agency.getText().toString(),
                                    startDate, startTime, endDate, endTime,
                                    vehicleUse.isChecked(), nightOut.isChecked())
                    );

                    final List<Shiftlog> shiftlogs = db.getAllShiftlogs();
                    Log.d("STORED_SHIFTLOGS", String.format("Number of ShiftLogs: %d", shiftlogs.size()));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            } else invalidDateTime();
                
            }

        });

    }

    public boolean validateTimes(){ //returns true if times and dates are valid.
        if (startTime.isEmpty() || endTime.isEmpty() || startDate.isEmpty() || endDate.isEmpty()){
            return false;
        }
        else if (startDatePicker.getYear() < endDatePicker.getYear()){
            return true;
        }
        else if(startDatePicker.getYear().equals(endDatePicker.getYear()) &&
                startDatePicker.getMonth() < endDatePicker.getMonth()){
            return true;
        }
        else if(startDatePicker.getMonth().equals(endDatePicker.getMonth()) &&
                startDatePicker.getDay() < endDatePicker.getDay()){
            return true;
        }
        else if(startDatePicker.getDay().equals(endDatePicker.getDay()) &&
                startTimePicker.getHour() < endTimePicker.getHour()){
            return true;
        }
        else return startTimePicker.getHour().equals(endTimePicker.getHour()) &&
                    startTimePicker.getMinute() < endTimePicker.getMinute();
    }

    public void invalidDateTime(){
        String message = "There appears to be an issue with your log:";
        if (startTime.isEmpty() || endTime.isEmpty() || startDate.isEmpty() || endDate.isEmpty()){
            message += "\nFill out both the start and end date and time.";
        }
        else {
            if (startDatePicker.getYear() > endDatePicker.getYear()) {
                message += "\nThe start year is set to after the end year";
            }
            else if (startDatePicker.getYear().equals(endDatePicker.getYear()) &&
                    startDatePicker.getMonth() > endDatePicker.getMonth()) {
                message += "\nThe start month is set to after the end month";
            }
            else if (startDatePicker.getMonth().equals(endDatePicker.getMonth()) &&
                    startDatePicker.getDay() > endDatePicker.getDay()) {
                message += "\nThe start day is set to after the end day";
            }
            else if (startDatePicker.getDay().equals(endDatePicker.getDay()) &&
                    startTimePicker.getHour() > endTimePicker.getHour()) {
                message += "\nThe start hour is set to after the end hour";
            }
            else if (startTimePicker.getHour().equals(endTimePicker.getHour()) &&
                    startTimePicker.getMinute() > endTimePicker.getMinute()) {
                message += "\nThe start minute is set to after the end minute";
            }
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTimePicked(int hourOfDay, int minute, int id) {
        String min = String.valueOf(minute);
        if (minute < 10) min = "0" + min;
        String hour = String.valueOf(hourOfDay);
        if (hourOfDay < 10) hour = "0" + hour;

        switch (id){ //id determines start (0) or end (1)
            case 0: startTimePicker.getTimeButton().setText("Start time - " + hour + ":" + min);
                startTimePicker.setHour(hourOfDay);
                startTimePicker.setMinute(minute);
                startTime = String.format(hour + ":" + min);
                break;
            case 1: endTimePicker.getTimeButton().setText("End time - " + hour + ":" + minute);
                endTimePicker.setHour(hourOfDay);
                endTimePicker.setMinute(minute);
                endTime = String.format(hour + ":" + min);
                break;
        }
    }

    @Override
    public void onDatePicked(int year, int month, int dayOfMonth, int id) {
        String mon = String.valueOf(month);
        if (month < 10) mon = "0" + mon;
        String day = String.valueOf(dayOfMonth);
        if (dayOfMonth < 10) day = "0" + day;

        switch (id){ //id determines start (0) or end (1)
            case 0: startDatePicker.getDateButton().setText(
                    "Start date - " + day + "/" + mon + "/" + year);
                startDatePicker.setYear(year);
                startDatePicker.setMonth(month);
                startDatePicker.setDay(dayOfMonth);
                startDate = day + "/" + mon + "/" + year;
                break;
            case 1: endDatePicker.getDateButton().setText(
                    "End date - " + day + "/" + mon + "/" + year);
                endDatePicker.setYear(year);
                endDatePicker.setMonth(month);
                endDatePicker.setDay(dayOfMonth);
                endDate = day + "/" + mon + "/" + year;
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final ShiftlogDAO db = Room.databaseBuilder(this,
                ShiftlogDatabase.class, "ShiftlogDatabase").build().shiftlogDAO();

        switch (item.getItemId()) {
            case R.id.past_logs:
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        final List<Shiftlog> allshiftlogs = db.getAllShiftlogs();
                        // fragment animation: https://stackoverflow.com/questions/4932462/animate-the-transition-between-fragments
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.setCustomAnimations(R.anim.slide_from_bottom, R.anim.slide_out_bottom);
                                transaction.replace(R.id.main_layout, PastShiftLogsFragment.newInstance(allshiftlogs));
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                        });
                    }
                });
                break;
            case R.id.ShiftLogs:
                //start Activity: https://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                startActivity(new Intent(MainActivity.this, MainActivity.class));

                   break;
                   default:
                        break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onListFragmentInteraction(final ShiftlogListItemView item) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final Shiftlog clickedShiftLog = db.getShiftLogById(item.getId());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.slide_from_bottom, R.anim.slide_out_bottom);
                        transaction.replace(R.id.main_layout, ShiftlogDetailFragment.newInstance(clickedShiftLog));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }
        });

    }
}
