package com.example.myapplication.myapplication.recordkeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private CheckBox vehcileUse;

    public void addListenerOnCheckBox() {
        vehcileUse = ((CheckBox)findViewById(R.id.vehicleUse));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
