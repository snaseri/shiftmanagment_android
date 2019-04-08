package com.example.myapplication.myapplication.recordkeeper;


import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.myapplication.recordkeeper.database.*;
import com.example.myapplication.myapplication.recordkeeper.views.ShiftlogListItemView;

import java.util.List;

import static com.example.myapplication.myapplication.recordkeeper.PastShiftLogsFragment.adapter;

// DateTime interface includes both custom Listeners for TimePicker and DatePicker
interface DateTime extends TimePickerFragment.TimePickedListener,
        DatePickerFragment.DatePickedListener{}

public class MainActivity extends AppCompatActivity
        implements DateTime, PastShiftLogsFragment.OnListFragmentInteractionListener {


    private AppCompatSpinner company;
    private AppCompatSpinner agency;
    private AppCompatButton saveButton;
    private AppCompatEditText registration;

    //Time pickers
    private TimePickerApp startTimePicker;
    private TimePickerApp endTimePicker;
    private TimePickerApp breakTimePicker;
    private TimePickerApp poaPicker;

    //Date pickers
    private DatePickerApp startDatePicker;
    private DatePickerApp endDatePicker;

    //Detail checkboxes
    private CheckBox vehicleUse;
    private CheckBox nightOut;
    private CheckBox useCompanyno;
    private CheckBox useAgencyyno;


    //Strings of the selected DateTimes
    private String startDate; private String startTime;
    private String endDate; private String endTime; private String breakTime; private String poa;

    private static ShiftlogDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        company = (findViewById(R.id.companyInput));
        agency = (findViewById(R.id.AgencyInput));
        saveButton = findViewById(R.id.Save_Button);
        registration =findViewById(R.id.RegInput);


        //Time pickers
        startTimePicker = new TimePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnStartTimePicker), 0);
        endTimePicker = new TimePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnEndTimePicker), 1);
        breakTimePicker = new TimePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnBreaks), 2);
        poaPicker= new TimePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnpoa), 3);


        //Date pickers
        startDatePicker = new DatePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnStartDatePicker), 0);
        endDatePicker = new DatePickerApp(getSupportFragmentManager(),
                (Button) findViewById(R.id.btnEndDatePicker), 1);

        //Detail checkboxes
        vehicleUse = (findViewById(R.id.vehicleUse));
        nightOut = (findViewById(R.id.nightOut));
        useCompanyno = (findViewById(R.id.useCompanyno));
        useAgencyyno = (findViewById(R.id.useAgencyyno));



        db = Room.databaseBuilder(this, ShiftlogDatabase.class,
                "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();
        setCompanyOptions();
        //Checkbox click listeners
        vehicleUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView registration = findViewById(R.id.Registration);
                AppCompatEditText registrationInput = findViewById(R.id.RegInput);
                Button poa = findViewById((R.id.btnpoa));

                if (((CheckBox) v).isChecked()) {

                    registration.setVisibility(View.VISIBLE);
                    registrationInput.setVisibility(View.VISIBLE);
                    poa.setVisibility(View.VISIBLE);

                    Toast.makeText(MainActivity.this,
                            "Vehicle operating selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    registration.setVisibility(View.INVISIBLE);
                    registrationInput.setVisibility(View.INVISIBLE);
                    poa.setVisibility(View.INVISIBLE);
                }

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

                    int sendid;
                    if (useAgencyyno.isChecked() && useCompanyno.isChecked()) {
                        sendid = 3;
                    } else if (useAgencyyno.isChecked() && !useCompanyno.isChecked()) {
                        sendid = 2;
                    } else if (!useAgencyyno.isChecked() && useCompanyno.isChecked()) {
                        sendid = 1;
                    } else {
                        sendid = 0;
                    }

                    db.insertShiftlog(
                            new Shiftlog(((Company) company.getSelectedItem()).getId(),
                                    ((Agency) agency.getSelectedItem()).getId(),
                                    sendid, startDate, startTime,endDate, endTime,breakTime,
                                    vehicleUse.isChecked(),registration.getText().toString(), poa,
                                    nightOut.isChecked(), false)

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
            reloadPage();
            } else invalidDateTime();
            }

        });

    }

    public void setCompanyOptions(){
        company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((Company)parent.getItemAtPosition(position)).getId() == -2) {
                    // Add new Company
                    parent.setSelection(0);
                    company.setSelection(0);
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    NewCompanyFragment.Callback callback = new NewCompanyFragment.Callback() {
                                        @Override
                                        public void reset() { setCompanyOptions(); }
                                    };
                                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                    transaction.setCustomAnimations(R.anim.slide_from_bottom, R.anim.slide_out_bottom);
                                    transaction.replace(R.id.main_layout, NewCompanyFragment.newInstance(callback));
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                            });
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final List<Company> companies = db.getAllCompanies();
                companies.add(0, new Company("No Company", "0"));
                companies.get(0).setId(-1);
                companies.add(new Company("Add Company", "0"));
                companies.get(companies.size() - 1).setId(-2);
                final ArrayAdapter<Company> adapter = new ArrayAdapter<Company>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, companies);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        company.setAdapter(adapter);
                    }
                });
            }
        }

        );


        agency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((Agency)parent.getItemAtPosition(position)).getId() == -2) {
                    // Add new Agency
                    parent.setSelection(0);
                    agency.setSelection(0);

                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    NewAgencyFragment.Callback callback = new NewAgencyFragment.Callback() {
                                        @Override
                                        public void reset() { setCompanyOptions(); }
                                    };
                                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                    transaction.setCustomAnimations(R.anim.slide_from_bottom, R.anim.slide_out_bottom);
                                    transaction.replace(R.id.main_layout, NewAgencyFragment.newInstance(callback));
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                            });
                        }
                    });
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final List<Agency> agencies = db.getAllAgencies();
                agencies.add(0, new Agency("No Agency", "0"));
                agencies.get(0).setId(-1);
                agencies.add(new Agency("Add Agency", "0"));
                agencies.get(agencies.size() - 1).setId(-2);
                final ArrayAdapter<Agency> adapter = new ArrayAdapter<Agency>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, agencies);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        agency.setAdapter(adapter);
                    }
                });
                }
          }

        );
    }

    public boolean logIsChanged() {
        if (company.getSelectedItem().toString().equalsIgnoreCase("No Company") &&
                agency.getSelectedItem().toString().equalsIgnoreCase("No Agency")  &&
                startTime == null && endTime == null && startDate == null && endDate == null &&
                breakTime == null &&
                !nightOut.isChecked() &&
                !vehicleUse.isChecked() &&
                !useAgencyyno.isChecked() &&
                !useCompanyno.isChecked()) {
            return false;
        } else { return true;}
    }

    //A method to reset all the values of all the shift log fields
    public void reloadPage() {
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }

    public boolean validateTimes() { //
        if (((Company) company.getSelectedItem()).getId() < 0
            &&((Agency) agency.getSelectedItem()).getId() < 0){return false;}
        else if (!(useAgencyyno.isChecked() || useCompanyno.isChecked())){return false;}
        if ((((Company) company.getSelectedItem()).getId() < 0 && useCompanyno.isChecked()) ||
                (((Agency) agency.getSelectedItem()).getId() < 0 && useAgencyyno.isChecked())){
            return false;
        }
        else if (startTime == null || endTime == null || startDate == null || endDate == null) {
            return false;
        } else if (startDatePicker.getYear() < endDatePicker.getYear()) {
            return true;
        } else if (startDatePicker.getYear().equals(endDatePicker.getYear()) &&
                startDatePicker.getMonth() < endDatePicker.getMonth()) {
            return true;
        } else if (startDatePicker.getMonth().equals(endDatePicker.getMonth()) &&
                startDatePicker.getDay() < endDatePicker.getDay()) {
            return true;
        } else if (startDatePicker.getDay().equals(endDatePicker.getDay()) &&
                startTimePicker.getHour() < endTimePicker.getHour()) {
            return true;
        } else return startTimePicker.getHour().equals(endTimePicker.getHour()) &&
                startTimePicker.getMinute() < endTimePicker.getMinute();

    }



    public void invalidDateTime(){
        String message = "There appears to be an issue with your log:";
        if (((Company) company.getSelectedItem()).getId() < 0
                &&((Agency) agency.getSelectedItem()).getId() < 0){
            message += "\nSet an agency or company";
        }
        if (!(useAgencyyno.isChecked() || useCompanyno.isChecked())){
            message += "\nSelect the company or agency to share the log with";
        }
        if ((((Company) company.getSelectedItem()).getId() < 0 && useCompanyno.isChecked()) ||
                (((Agency) agency.getSelectedItem()).getId() < 0 && useAgencyyno.isChecked())){
            message += "\nOnly select company or agency to share with if chosen";
        }
        if (startTime == null || endTime == null || startDate == null || endDate == null){
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
            case 1: endTimePicker.getTimeButton().setText("End time - " + hour + ":" + min);
                endTimePicker.setHour(hourOfDay);
                endTimePicker.setMinute(minute);
                endTime = String.format(hour + ":" + min);
                break;
            case 2: breakTimePicker.getTimeButton().setText("Break time - " + hour + ":" + min);
                breakTimePicker.setHour(hourOfDay);
               breakTimePicker.setMinute(minute);
                breakTime = String.format(hour + ":" + min);
                break;
            case 3: poaPicker.getTimeButton().setText("POA - " + hour + ":" + min);
                poaPicker.setHour(hourOfDay);
                poaPicker.setMinute(minute);
                poa = String.format(hour + ":" + min);
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


    public boolean setToOtherMenu = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        if (setToOtherMenu) {
            menu.findItem(R.id.menu_search_bar).setVisible(true);
        }
        MenuItem searchItem = menu.findItem(R.id.menu_search_bar);
        SearchView searchView = (SearchView) searchItem.getActionView();
        
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final ShiftlogDAO db = Room.databaseBuilder(this,
                ShiftlogDatabase.class, "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();

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
                final Company c = db.getCompanyByID(clickedShiftLog.getCompany());
                final Agency a = db.getAgencyByID(clickedShiftLog.getAgency());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.slide_from_bottom, R.anim.slide_out_bottom);
                        transaction.replace(R.id.main_layout, ShiftlogDetailFragment.newInstance(clickedShiftLog, c, a));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }
        });

    }

}
