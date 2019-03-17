package com.example.myapplication.myapplication.recordkeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox vehcileUse;
    private CheckBox nightout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vehcileUse = ((CheckBox)findViewById(R.id.vehicleUse));
        nightout = ((CheckBox)findViewById(R.id.nightout));
        AppCompatButton saveButton = findViewById(R.id.Save_Button);

        

        //Save button Listner
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override  //setting what happens when clicked below
            public void onClick(View v) {
                StringBuffer result = new StringBuffer();
                result.append("Vehicle Use: ").append(vehcileUse.isChecked());
                result.append("Staying out at nights: ").append(nightout.isChecked());

                Toast.makeText(  //toast pop up message creation
                        getApplicationContext(),  // came as this good if this cant b used
//                        String.format ("Your shift log has been saved"),
                        //Testing the checkboxes saved values
                        result.toString(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
