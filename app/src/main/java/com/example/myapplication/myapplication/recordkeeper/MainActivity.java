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

interface Dialog extends TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{}

public class MainActivity extends AppCompatActivity
        implements Dialog {

    private static final String TAG = "MainActivity";

    private CheckBox vehcileUse;
    private CheckBox nightout;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        vehcileUse = ((CheckBox)findViewById(R.id.vehicleUse));
        nightout = ((CheckBox)findViewById(R.id.nightout));
        AppCompatButton saveButton = findViewById(R.id.Save_Button);




        //Time picker
        Button startTimePicker = (Button) findViewById(R.id.btnStartTimePicker);
        Button endTimePicker = (Button) findViewById(R.id.btnEndTimePicker);

        TimePickerApp startTime = new TimePickerApp(getSupportFragmentManager(), startTimePicker);
        TimePickerApp endTime = new TimePickerApp(getSupportFragmentManager(), endTimePicker);



        //Date picker

        Button startDatePicker = (Button) findViewById(R.id.btnStartDatePicker);
        Button endDatePicker = (Button) findViewById(R.id.btnEndDatePicker);

        DatePickerApp startDate = new DatePickerApp(getSupportFragmentManager(), startDatePicker);
        DatePickerApp endDate = new DatePickerApp(getSupportFragmentManager(), endDatePicker);



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
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(  //toast pop up message creation
                getApplicationContext(),  // came as this good if this cant b used
                String.format("Selected Date: " + dayOfMonth + "/" + month + "/" + year),
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
