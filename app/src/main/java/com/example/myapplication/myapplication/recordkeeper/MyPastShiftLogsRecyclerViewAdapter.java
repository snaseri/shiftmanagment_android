package com.example.myapplication.myapplication.recordkeeper;

import android.app.Application;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.myapplication.recordkeeper.PastShiftLogsFragment.OnListFragmentInteractionListener;
import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;
import com.example.myapplication.myapplication.recordkeeper.views.ShiftlogListItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ShiftlogListItemView} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPastShiftLogsRecyclerViewAdapter extends RecyclerView.Adapter<MyPastShiftLogsRecyclerViewAdapter.ViewHolder> {

    private Button mShareButton;
    private final List<ShiftlogListItemView> mValues = new ArrayList<>();
    private final List<Button> mButtons = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;
    private static  OnListFragmentInteractionListener mButtonListener;





    public MyPastShiftLogsRecyclerViewAdapter(List<Shiftlog> items, OnListFragmentInteractionListener listener) {
        for (Shiftlog shiftlog : items) {
            mValues.add(new ShiftlogListItemView(shiftlog));
        }
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pastshiftlogs2, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mShitLogNameView.setText(mValues.get(position).getCompany());
        holder.mStartTextView.setText(mValues.get(position).getStartDate());
        holder.mShareButton.setTag(position);

        holder.mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    Toast.makeText(v.getContext(), String.format("Shared Log: " + holder.mItem.getCompany(), holder.mItem.getStartDate(), holder.mItem.getEndDate()), Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mShitLogNameView;
        public final TextView mStartTextView;
        public ShiftlogListItemView mItem;
        public Button mShareButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mShitLogNameView = (TextView) view.findViewById(R.id.CompanyName);
            mStartTextView =(TextView) view.findViewById(R.id.start_date);
            mShareButton = (Button) view.findViewById(R.id.share_button);

        }
    }
}
