package com.example.myapplication.myapplication.recordkeeper;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

interface DateTime extends TimePickerFragment.TimePickedListener,
                            DatePickerFragment.DatePickedListener{}
public class MainActivity extends AppCompatActivity
        implements DateTime {

    private static final String TAG = "MainActivity";
    private TimePickerApp startTimePicker;
    private TimePickerApp endTimePicker;
    private DatePickerApp startDatePicker;
    private DatePickerApp endDatePicker;
    private CheckBox vehicleUse;
    private CheckBox nightout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicleUse = ((CheckBox)findViewById(R.id.vehicleUse));
        nightout = ((CheckBox)findViewById(R.id.nightout));
        AppCompatButton saveButton = findViewById(R.id.Save_Button);




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
                result.append("Vehicle Use: ").append(vehicleUse.isChecked());
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
    public void onTimePicked(int hourOfDay, int minute, int id) {
        switch (id){
            case 0: startTimePicker.getTimeButton().setText(hourOfDay + ":" + minute);
                    startTimePicker.setHour(hourOfDay);
                    startTimePicker.setMinute(minute);
                    break;
            case 1: endTimePicker.getTimeButton().setText(hourOfDay + ":" + minute);
                    endTimePicker.setHour(hourOfDay);
                    endTimePicker.setMinute(minute);
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
                    break;
            case 1: endDatePicker.getDateButton().setText(dayOfMonth + "/" + month + "/" + year);
                    endDatePicker.setYear(year);
                    endDatePicker.setMonth(month);
                    endDatePicker.setDay(dayOfMonth);
                    break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

}
