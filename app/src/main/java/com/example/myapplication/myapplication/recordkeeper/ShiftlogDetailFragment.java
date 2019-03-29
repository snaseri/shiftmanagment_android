package com.example.myapplication.myapplication.recordkeeper;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShiftlogDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShiftlogDetailFragment extends Fragment {


    private static final String SHIFTLOG_COMPANY_ARG = "shiftlog_company";
    private static final String SHIFTLOG_AGENCY_ARG = "shiftlog_agency";
    private static final String SHIFTLOG_START_TIME_ARG = "shiftlog_start_time";
    private static final String SHIFTLOG_END_TIME_ARG = "shiftlog_end_time";
    private static final String SHIFTLOG_START_DATE_ARG = "shiftlog_start_date";
    private static final String SHIFTLOG_END_DATE_ARG = "shiftlog_end_date";
    private static final String SHIFTLOG_REGISTRATION_ARG ="Shitlog_registration";




    private String shiftlog_company;
    private String shiftlog_agency;
    private String shiftlog_start_time;
    private String shiftlog_end_time;
    private String shiftlog_start_date;
    private String shiftlog_end_date;
    private String shiftlog_registration;


    public ShiftlogDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShiftlogDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShiftlogDetailFragment newInstance(Shiftlog details) {
        ShiftlogDetailFragment fragment = new ShiftlogDetailFragment();
        Bundle args = new Bundle();


        args.putString(SHIFTLOG_COMPANY_ARG, details.getCompany());
        args.putString(SHIFTLOG_AGENCY_ARG, details.getAgency());
        args.putString(SHIFTLOG_START_TIME_ARG, details.getStartTime());
        args.putString(SHIFTLOG_END_TIME_ARG, details.getEndTime());
        args.putString(SHIFTLOG_START_DATE_ARG, details.getStartDate());
        args.putString(SHIFTLOG_END_DATE_ARG, details.getEndDate());
        args.putString(SHIFTLOG_REGISTRATION_ARG, details.getRegistration());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            this.shiftlog_company = getArguments().getString(SHIFTLOG_COMPANY_ARG);
            this.shiftlog_agency = getArguments().getString(SHIFTLOG_AGENCY_ARG);
            this.shiftlog_start_time = getArguments().getString(SHIFTLOG_START_TIME_ARG);
            this.shiftlog_end_time = getArguments().getString(SHIFTLOG_END_TIME_ARG);
            this.shiftlog_start_date = getArguments().getString(SHIFTLOG_START_DATE_ARG);
            this.shiftlog_end_date = getArguments().getString(SHIFTLOG_END_DATE_ARG);
            this.shiftlog_registration = getArguments().getString(SHIFTLOG_END_DATE_ARG);

            
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_shiftlog_detail, container, false);

        ((AppCompatTextView) v.findViewById(R.id.company)).setText(shiftlog_company);
        ((AppCompatTextView) v.findViewById(R.id.agency)).setText(shiftlog_agency);
        ((AppCompatTextView) v.findViewById(R.id.start_time)).setText(shiftlog_start_time);
        ((AppCompatTextView) v.findViewById(R.id.end_time)).setText(shiftlog_end_time);
        ((AppCompatTextView) v.findViewById(R.id.start_date)).setText(shiftlog_start_date);
        ((AppCompatTextView) v.findViewById(R.id.end_date)).setText(shiftlog_end_date);
        ((AppCompatTextView) v.findViewById(R.id.Registration)).setText(shiftlog_registration);


        return v;
    }

}
