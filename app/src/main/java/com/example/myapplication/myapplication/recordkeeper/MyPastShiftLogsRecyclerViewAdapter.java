package com.example.myapplication.myapplication.recordkeeper;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.myapplication.recordkeeper.PastShiftLogsFragment.OnListFragmentInteractionListener;
import com.example.myapplication.myapplication.recordkeeper.database.Agency;
import com.example.myapplication.myapplication.recordkeeper.database.Company;
import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDatabase;
import com.example.myapplication.myapplication.recordkeeper.views.ShiftlogListItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ShiftlogListItemView} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */

public class MyPastShiftLogsRecyclerViewAdapter extends RecyclerView.Adapter<MyPastShiftLogsRecyclerViewAdapter.ViewHolder> implements Filterable {

    private Button mShareButton;
    private final List<ShiftlogListItemView> mValues = new ArrayList<>();
    private List<Shiftlog> mLogValues = new ArrayList<>();
    private List<Company> companies;
    private List<Agency> agencies;
    private final List<ShiftlogListItemView> mValuesComplete = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;
    private ActionMode mActionMode;
    private List<Shiftlog> mCheckBoxSelected = new ArrayList<>();
    private List<ViewHolder> mAllViewHolders = new ArrayList<>();

    private List<ViewHolder> mSelectedViewHolders = new ArrayList<>();

    private List<CheckBox> mCheckBoxs = new ArrayList<>();

    private List readyToShareLogs = new ArrayList();
    private static  OnListFragmentInteractionListener mButtonListener;
    private boolean pageSwitched;
    private Context context;
    private List<Shiftlog> items;
    private static ShiftlogDAO db;

    private String companyName;
    private String agencyName;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

//save the context recievied via constructor in a local variable

    public MyPastShiftLogsRecyclerViewAdapter(Context c, List<Shiftlog> items, OnListFragmentInteractionListener listener) {


        this.items = items;
        Log.d("Test", String.valueOf(items.size()));
        for (Shiftlog shiftlog : items) {
            ShiftlogListItemView toAdd = new ShiftlogListItemView(shiftlog, c, mValues.size());
            toAdd.setUpdate(new ShiftlogListItemView.UpdateUI() {
                @Override
                public void update(final int i) {
                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ViewHolder item = mAllViewHolders.get(i);
                            item.mShitLogNameView.setText(item.mItem.getCompany());
                        }
                    });
                }
            });

                mValues.add(toAdd);
                mLogValues.add(shiftlog);
        }
        mValuesComplete.addAll(mValues);
        Log.d("SIZE aaa", String.valueOf(mValues.size()));
        Log.d("SIZE", String.valueOf(mAllViewHolders.size()));
        this.context = c;
        mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        "".split(",\n");

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_pastshiftlogs2, parent, false);
            ViewHolder vh = new ViewHolder(view);

            return vh;
        }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        if (holder.mItem.getShared()){
            holder.mView.setBackgroundColor(Color.GRAY);
        }
        holder.mItem.generateName();
        holder.mStartTextView.setText(mValues.get(position).getStartDate());
        holder.mShitLogNameView.setText(mValues.get(position).getCompany());
        holder.mStartTextView.setText("Start date: " + mValues.get(position).getStartDate());
        holder.mSelectedLogs.setTag(position);
        Log.d("text", String.valueOf(position));
        Log.d("text4", String.valueOf(mLogValues.size()));
        final Shiftlog mShiftlog = mLogValues.get(position);

        holder.mSelectedLogs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    if (holder.mSelectedLogs.isChecked()) {
                        mCheckBoxs.add(holder.mSelectedLogs);
                        mCheckBoxSelected.add(mShiftlog);
                        mSelectedViewHolders.add(holder);
                        Log.d("PAST_LOGS: ",String.format(holder.mItem.getId() + "Added to Selected list. Current list:" + mCheckBoxSelected.toString()));
                    } else if (!holder.mSelectedLogs.isChecked()) {
                        mCheckBoxs.remove(holder.mSelectedLogs);
                        mCheckBoxSelected.remove(mShiftlog);
                        mSelectedViewHolders.remove(holder);
                        Log.d("PAST_LOGS: ",String.format(holder.mItem.getId() + "Removed from Selected list. Current list:" + mCheckBoxSelected.toString()));
                    }

                    mActionMode = ((AppCompatActivity)v.getContext()).startSupportActionMode(mActionModeCallback);

//                    Toast.makeText(v.getContext(), String.format("Selected: " + holder.mItem.getName()), Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    pageSwitched = true;
                    mActionMode = ((AppCompatActivity)v.getContext()).startSupportActionMode(mActionModeCallback);
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            if (pageSwitched) {
                mActionMode = null;
                return false;
            }
            if (!mCheckBoxSelected.isEmpty()) {
                actionMode.getMenuInflater().inflate(R.menu.pastlog_share_menu, menu);
                actionMode.setTitle("Choose your option: ");
                return true;
            } else {
                mActionMode = null;
                return false;
            }
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

            db = Room.databaseBuilder(context, ShiftlogDatabase.class,
                    "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();

            switch (menuItem.getItemId()) {
                // Delete button
                case R.id.option_1:
                    AlertDialog.Builder altdial = new AlertDialog.Builder(context);
                    altdial.setMessage("Are you sure you want to delete these shift logs?").setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (Shiftlog s : mCheckBoxSelected) {
                                        final int itemPosition = mLogValues.indexOf(s);
                                        Log.d("ITEM POSITION", String.valueOf(itemPosition));

                                        mValues.remove(itemPosition);
                                        mLogValues.remove(itemPosition);
                                        mValuesComplete.remove(itemPosition);
                                        final Shiftlog logToGet = s;
                                        AsyncTask.execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                db.deleteShiftlogsbyid(logToGet.getId());
                                            }
                                        });

                                        notifyItemRemoved(itemPosition);
                                        //notifyDataSetChanged();

                                    }

                                    mCheckBoxs.clear();
                                    mCheckBoxSelected.clear();
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert = altdial.create();
                    alert.setTitle("Deleting Shift-logs");
                    alert.show();
                    mActionMode = null;
                    return false;
                //Share button
                case R.id.option_2:
                    final ArrayList<String> testlist = new ArrayList<>();
                    for (Shiftlog s : mCheckBoxSelected) {
                        final Shiftlog logToGet = s;
                        final String textMessage1;
                        final String textMessage2;
                        String companyName;
                        String agencyName;


                        if (s.getVehicleUse()) {
                            textMessage1 = String.format(
                                    "Shift Log Summary: " + System.getProperty("line.separator") +
                                            "Start Date: " + s.getStartDate() + System.getProperty("line.separator") +
                                            "Start Time: " + s.getStartTime() + System.getProperty("line.separator") +
                                            "End Date: " + s.getEndDate() + System.getProperty("line.separator") +
                                            "End Time: " + s.getEndTime());
                            textMessage2 = String.format(
                                            "Break Time: " + s.getBreaks() + System.getProperty("line.separator") +
                                            "Nights out: " + s.getNightOut() + System.getProperty("line.separator") +
                                            "Registered Vehicle: " + s.getVehicleUse() + System.getProperty("line.separator") +
                                            "Vehicle Registration: " + s.getRegistration() + System.getProperty("line.separator") +
                                            "POA: " + s.getPoa());

                        } else {
                            textMessage1 = String.format(
                                    "Shift Log Summary: " + System.getProperty("line.separator") +
                                            "Start Date: " + s.getStartDate() + System.getProperty("line.separator") +
                                            "Start Time: " + s.getStartTime() + System.getProperty("line.separator") +
                                            "End Date: " + s.getEndDate() + System.getProperty("line.separator") +
                                            "End Time: " + s.getEndTime());
                            textMessage2 = String.format(
                                            "Break Time: " + s.getBreaks() + System.getProperty("line.separator") +
                                            "Nights out: " + s.getNightOut());

                        }

                        //SMS MESSANGER

                        //Checking for sms permission
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(context, "This app doesn't have permission to send text", Toast.LENGTH_LONG).show();
                            ActivityCompat.requestPermissions((MainActivity) context, new String[]{Manifest.permission.SEND_SMS}, 1);
                        } else {
                            Toast.makeText(context, "Sending...", Toast.LENGTH_LONG).show();
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TESTING", String.format("receiver is " + logToGet.getShareWith()));
                                        // Send to Company
                                    if (logToGet.getShareWith()% 2 == 1) {
                                        SmsManager.getDefault().sendTextMessage(db.getCompanyByID(logToGet.getCompany()).getPhoneNumber()
                                                , null, textMessage1, null, null);
                                        SmsManager.getDefault().sendTextMessage(db.getCompanyByID(logToGet.getCompany()).getPhoneNumber()
                                                , null, textMessage2, null, null);
                                        //Send to Agency
                                    }
                                    if (logToGet.getShareWith() >= 2) {
                                        SmsManager.getDefault().sendTextMessage(db.getAgencyByID(logToGet.getAgency()).getPhoneNumber()
                                                , null, textMessage1, null, null);
                                        SmsManager.getDefault().sendTextMessage(db.getAgencyByID(logToGet.getAgency()).getPhoneNumber()
                                                , null, textMessage2, null, null);

                                    }
                                }
                            });
                        }

                        final ShiftlogDAO db = Room.databaseBuilder(context, ShiftlogDatabase.class, "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();
                        for (final ViewHolder v : mSelectedViewHolders) {
                            v.mView.setBackgroundColor(Color.GRAY);
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    db.setSharedFor(v.mItem.getId());
                                }
                            });
                        }

                    }

                    for (CheckBox c : mCheckBoxs) {
                        c.setChecked(false);
                    }
                    mCheckBoxs.clear();
                    mCheckBoxSelected.clear();
                    mActionMode = null;
                    return false;
                default:
                    mActionMode = null;
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
        }
    };
    
    @Override
    public Filter getFilter() {
        return logFilter;
    }
    
    private Filter logFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ShiftlogListItemView> filteredList = new ArrayList<>();
            
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mValuesComplete);
            } else  {
                String filterpattern = constraint.toString().toLowerCase().trim();
                for (ShiftlogListItemView shiftlog : mValuesComplete) {
                    shiftlog.generateName();
                    Log.d("COMPANY NAME " , shiftlog.getCompany());
                    if (shiftlog.getStartDate().contains(filterpattern)) {
                        filteredList.add(shiftlog);
                    }
                    if (shiftlog.getCompany().toLowerCase().contains(filterpattern)) {
                        filteredList.add(shiftlog);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            
            return results; 
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mValues.clear();
            mValues.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    
    
    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mShitLogNameView;
        public final TextView mStartTextView;
        public ShiftlogListItemView mItem;
        public CheckBox mSelectedLogs;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mShitLogNameView = (TextView) view.findViewById(R.id.CompanyName);
            mStartTextView =(TextView) view.findViewById(R.id.start_date);
            mSelectedLogs = (CheckBox) view.findViewById(R.id.shiftSelect);
            mAllViewHolders.add(this);

        }
    }
}
