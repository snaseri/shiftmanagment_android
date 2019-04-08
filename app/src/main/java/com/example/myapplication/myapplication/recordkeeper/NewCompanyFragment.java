package com.example.myapplication.myapplication.recordkeeper;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.myapplication.recordkeeper.database.Agency;
import com.example.myapplication.myapplication.recordkeeper.database.Company;
import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDatabase;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewCompanyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewCompanyFragment extends Fragment {
    Callback callback;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    interface Callback{
        void reset();
    }


    public NewCompanyFragment() {
        // Required empty public constructor
    }

    public static NewCompanyFragment newInstance(Callback callback) {
        NewCompanyFragment fragment = new NewCompanyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.callback = callback;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_new_company, container, false);

        view.findViewById(R.id.Company_Save_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText phoneNumber = ((EditText) view.findViewById(R.id.phoneNumberInput));
                if(phoneNumber.getText().length() != 11) {
                    Toast.makeText(view.getContext(), "Phone number must be 11 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!Pattern.matches("\\d+", phoneNumber.getText())) {
                    Toast.makeText(view.getContext(), "Phone number must be all numbers", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    final ShiftlogDAO db = Room.databaseBuilder(getContext(), ShiftlogDatabase.class,
                            "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();
                    final String name = ((EditText)view.findViewById(R.id.CompanyInput)).getText().toString();

                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            db.insertCompany(new Company(name, phoneNumber.getText().toString()));
                            if (getFragmentManager() != null) {
                                getFragmentManager().popBackStack();
                            }
                            callback.reset();
                        }
                    });
                    Toast.makeText(view.getContext(), "Company "+name+" has been added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
