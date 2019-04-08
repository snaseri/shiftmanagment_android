package com.example.myapplication.myapplication.recordkeeper;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.myapplication.recordkeeper.database.Agency;
import com.example.myapplication.myapplication.recordkeeper.database.Company;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDatabase;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewAgencyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewAgencyFragment extends Fragment {
    Callback callback;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    interface Callback{
        void reset();
    }

    public NewAgencyFragment() {
        // Required empty public constructor
    }

    public static NewAgencyFragment newInstance(Callback callback) {
        NewAgencyFragment fragment = new NewAgencyFragment();
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
        final View view = inflater.inflate(R.layout.activity_new_agency, container, false);

        view.findViewById(R.id.Agency_Save_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText phoneNumber = ((EditText) view.findViewById(R.id.phoneNumberInput));

                if(invalidValidateLength(phoneNumber.getText().length()) ) {
                    Toast.makeText(view.getContext(), "Phone number must be 11 digits", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(invalidValidateType(phoneNumber.getText().toString())){
                    Toast.makeText(view.getContext(), "Phone number must be all numbers", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    final ShiftlogDAO db = Room.databaseBuilder(getContext(), ShiftlogDatabase.class,
                            "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();
                    final String name = ((EditText)view.findViewById(R.id.AgencyInput)).getText().toString();
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            db.insertAgency(new Agency(name, phoneNumber.getText().toString()));
                            if (getFragmentManager() != null) {
                                getFragmentManager().popBackStack();
                            }
                            callback.reset();
                        }
                    });
                    Toast.makeText(view.getContext(), "Agency "+name+" has been added", Toast.LENGTH_SHORT).show();
                }
            }
        });


       return view;


    }


    public static boolean invalidValidateLength(Integer numberLength){
        if(numberLength != 11) {
            return false;
        }
        else return true;
    }

    public static boolean invalidValidateType( String dataType){
        if(!Pattern.matches("\\d+", dataType)) {
            return false;
        }
        else return true;
    }
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

