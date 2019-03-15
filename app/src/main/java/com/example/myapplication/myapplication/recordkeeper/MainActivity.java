package com.example.myapplication.myapplication.recordkeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton saveButton = findViewById(R.id.Save_Button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override  //setting what happens when clicked below
            public void onClick(View v) {

                Toast.makeText(  //toast pop up message creation
                        getApplicationContext(),  // came as this good if this cant b used
                        String.format ("Your shift log has been saved"),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
