package com.qr.epics.epicsr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Raja on 29-Jan-18.
 */

public class homePageListAdapter extends RecyclerView.Adapter<homePageListAdapter.SubHolder> {
    public ArrayList<homePageItems> mDataSet;
    private Context context;

    public static class SubHolder extends RecyclerView.ViewHolder {

        private TextView mItemName;

        public SubHolder(final View v) {
            super(v);
            mItemName = (TextView) v.findViewById(R.id.busID);
        }
    }

    public homePageListAdapter(Context context, ArrayList<homePageItems> myDataSet) {
        this.context = context;
        mDataSet = myDataSet;
    }

    public homePageListAdapter.SubHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_item, parent, false);
        return new SubHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(SubHolder holder, int position) {
        holder.mItemName.setText(mDataSet.get(position).getSs());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}