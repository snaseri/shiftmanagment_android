package com.example.myapplication.myapplication.recordkeeper;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    private final List<ShiftlogListItemView> mValues = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {


    }

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mShitLogNameView.setText(mValues.get(position).getName());

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
        public ShiftlogListItemView mItem;
        public ImageView mShareButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mShitLogNameView = (TextView) view.findViewById(R.id.shiftlog_name);
            mShareButton = (ImageView) view.findViewById(R.id.image_share);
        }
    }
}
