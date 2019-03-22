package com.example.myapplication.myapplication.recordkeeper;


import android.arch.persistence.room.Room;
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

interface DateTime extends TimePickerFragment.TimePickedListener,
        DatePickerFragment.DatePickedListener{}

public class MainActivity extends AppCompatActivity
        implements DateTime, PastShiftLogsFragment.OnListFragmentInteractionListener {

    private AppCompatEditText name;
    private AppCompatEditText company;
    private AppCompatEditText agency;
    private TimePickerApp startTimePicker;
    private TimePickerApp endTimePicker;
    private DatePickerApp startDatePicker;
    private DatePickerApp endDatePicker;
    private CheckBox vehicleUse;
    private CheckBox nightout;

    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    private static ShiftlogDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = ((AppCompatEditText)findViewById(R.id.nameInput));
        company = ((AppCompatEditText)findViewById(R.id.companyInput));
        agency = ((AppCompatEditText)findViewById(R.id.AgencyInput));
        vehicleUse = ((CheckBox)findViewById(R.id.vehicleUse));
        nightout = ((CheckBox)findViewById(R.id.nightout));
        AppCompatButton saveButton = findViewById(R.id.Save_Button);

        db = Room.databaseBuilder(this, ShiftlogDatabase.class,
                "ShiftlogDatabase").build().shiftlogDAO();


        //Time picker
        startTimePicker = new TimePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnStartTimePicker), 0);
        endTimePicker = new TimePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnEndTimePicker), 1);

        //Date picker
        startDatePicker = new DatePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnStartDatePicker), 0);
        endDatePicker = new DatePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnEndDatePicker), 1);



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

        nightout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //Save button Listner
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override  //setting what happens when clicked below
            public void onClick(View v) {



                AsyncTask.execute(new Runnable() {


                    @Override
                    public void run() {

                        db.insertShiftlog(
                                new Shiftlog(name.getText().toString(), company.getText().toString(), agency.getText().toString(),
                                        startDate,startTime,endDate,endTime,
                                        vehicleUse.isChecked(),nightout.isChecked())
                        );

                        final List<Shiftlog> shiftlogs = db.getAllShiftlogs();
                        Log.d("STORED_SHIFTLOGS", String.format("Number of ShiftLogs: %d", shiftlogs.size()));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Saved",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                
            }

        });

    }

    @Override
    public void onTimePicked(int hourOfDay, int minute, int id) {
        switch (id){
            case 0: startTimePicker.getTimeButton().setText(hourOfDay + ":" + minute);
                startTimePicker.setHour(hourOfDay);
                startTimePicker.setMinute(minute);
                startTime = String.format(hourOfDay + ":" + minute);
                break;
            case 1: endTimePicker.getTimeButton().setText(hourOfDay + ":" + minute);
                endTimePicker.setHour(hourOfDay);
                endTimePicker.setMinute(minute);
                endTime = String.format(hourOfDay + ":" + minute);
                break;
        }
    }

    @Override
    public void onDatePicked(int year, int month, int dayOfMonth, int id) {
        System.out.println(id);
        switch (id){
            case 0: startDatePicker.getDateButton().setText(dayOfMonth + "/" + month + "/" + year);
                startDatePicker.setYear(year);
                startDatePicker.setMonth(month);
                startDatePicker.setDay(dayOfMonth);
                startDate = String.format(dayOfMonth + "/" + month + "/" + year);
                break;
            case 1: endDatePicker.getDateButton().setText(dayOfMonth + "/" + month + "/" + year);
                endDatePicker.setYear(year);
                endDatePicker.setMonth(month);
                endDatePicker.setDay(dayOfMonth);
                endDate = String.format(dayOfMonth + "/" + month + "/" + year);
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
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final List<Shiftlog> allshiftlogs = db.getAllShiftlogs();
                switch (item.getItemId()) {
                    case R.id.past_logs:
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

                        break;
                    default:
                        break;
                }
            }
        });
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
