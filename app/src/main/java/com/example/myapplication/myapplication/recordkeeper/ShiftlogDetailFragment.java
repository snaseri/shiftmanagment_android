package com.example.myapplication.myapplication.recordkeeper;


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

    private static final String SHIFTLOG_NAME_ARG = "shiftlog_name";
    private static final String SHIFTLOG_COMPANY_ARG = "shiftlog_company";

    private String shiftlog_name;
    private String shiftlog_company;

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

        args.putString(SHIFTLOG_NAME_ARG,details.getName());
        args.putString(SHIFTLOG_COMPANY_ARG, details.getCompany());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.shiftlog_name = getArguments().getString(SHIFTLOG_NAME_ARG);
            this.shiftlog_company = getArguments().getString(SHIFTLOG_COMPANY_ARG);

            // TODO: Rest
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_shiftlog_detail, container, false);

        ((AppCompatTextView) v.findViewById(R.id.name)).setText(shiftlog_name);
        ((AppCompatTextView) v.findViewById(R.id.company)).setText(shiftlog_company);


        return v;
    }

}
