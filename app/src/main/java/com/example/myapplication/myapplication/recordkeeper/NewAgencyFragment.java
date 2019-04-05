package com.example.myapplication.myapplication.recordkeeper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewAgencyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewAgencyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public NewAgencyFragment() {
        // Required empty public constructor
    }

    public static NewAgencyFragment newInstance() {
        NewAgencyFragment fragment = new NewAgencyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_new_agency, container, false);

        view.findViewById(R.id.Agency_Save_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText phoneNumber = ((EditText) view.findViewById(R.id.phoneNumberInput));
                if(phoneNumber.getText().length() != 11) {
                    Toast.makeText(view.getContext(), "Phone number must be 11 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Pattern.matches("\\d+", phoneNumber.getText())) {
                    Toast.makeText(view.getContext(), "Phone number must be all numbers", Toast.LENGTH_SHORT).show();
                    return;
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
