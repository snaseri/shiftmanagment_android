package com.example.myapplication.myapplication.recordkeeper;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "MainActivity";

    private CheckBox vehcileUse;
    private CheckBox nightout;
    private TextView mDisplayStartDate;
    private TextView mDisplayEndDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDisplayStartDate = (TextView) findViewById(R.id.btnStartDatePicker);
        mDisplayEndDate = (TextView) findViewById(R.id.btnEndDatePicker);
        vehcileUse = ((CheckBox)findViewById(R.id.vehicleUse));
        nightout = ((CheckBox)findViewById(R.id.nightout));
        AppCompatButton saveButton = findViewById(R.id.Save_Button);




        //Time picker
        Button startTimePicker = (Button) findViewById(R.id.btnStartTimePicker);
        Button endTimePicker = (Button) findViewById(R.id.btnEndTimePicker);

        TimePickerApp startTime = new TimePickerApp(getSupportFragmentManager(), startTimePicker);

        startTimePicker.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               DialogFragment timePicker = new TimePickerFragment();
               timePicker.show(getSupportFragmentManager(), "Select Start Time");

           }
        });

        endTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Select End Time");

            }
        });

        //Date Listener

        mDisplayStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Black,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();
            }

        });

        mDisplayEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Black,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();

            }

        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + dayOfMonth + "/" + month + "/" + year);
                Toast.makeText(  //toast pop up message creation
                        getApplicationContext(),  // came as this good if this cant b used
                        String.format("Selected Date: " + dayOfMonth + "/" + month + "/" + year),
                        Toast.LENGTH_SHORT
                ).show();
            }
        };


        //Checkbox click listeners
        vehcileUse.setOnClickListener(new View.OnClickListener() {
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
                if (((CheckBox)v).isChecked()) {
                    Toast.makeText(MainActivity.this,
                            "Nights out availability selected",Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Save button Listner
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override  //setting what happens when clicked below
            public void onClick(View v) {
                StringBuffer result = new StringBuffer();
                result.append("Vehicle Use: ").append(vehcileUse.isChecked());
                result.append("Staying out at nights: ").append(nightout.isChecked());

                Toast.makeText(  //toast pop up message creation
                        getApplicationContext(),  // came as this good if this cant b used
                        result.toString(),
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(  //toast pop up message creation
                getApplicationContext(),  // came as this good if this cant b used
                String.format("Selected Time: " + hourOfDay + ":" + minute),
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }
}
