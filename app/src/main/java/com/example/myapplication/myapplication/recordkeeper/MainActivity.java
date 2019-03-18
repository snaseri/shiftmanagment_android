package com.example.myapplication.myapplication.recordkeeper;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CheckBox vehcileUse;
    private CheckBox nightout;
    private TextView mDisplayStartDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDisplayStartDate = (TextView) findViewById(R.id.btnStartDatePicker);
        vehcileUse = ((CheckBox)findViewById(R.id.vehicleUse));
        nightout = ((CheckBox)findViewById(R.id.nightout));
        AppCompatButton saveButton = findViewById(R.id.Save_Button);


        //Date Listener



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
}
