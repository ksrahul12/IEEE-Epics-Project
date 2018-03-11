package com.qr.epics.epicsr;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Raja on 11-Jan-18.
 */



public class walletHistory_Adapter extends RecyclerView.Adapter< walletHistory_Adapter.SubHolder> {

    public ArrayList<walletHistoryItems> masterDataSet,infoDataSet;
    private Context context;


    public static class SubHolder extends RecyclerView.ViewHolder
    {
        private TextView walletHistoryItem_amount;
        ImageView transactionImage;


        public SubHolder(final View v) {
            super(v);


            walletHistoryItem_amount = (TextView) v.findViewById(R.id.walletHistoryItemAmount);
            transactionImage = (ImageView) v.findViewById(R.id.transacImage);


        }
    }

    public walletHistory_Adapter(Context context, ArrayList<walletHistoryItems> myDataSet) {
        this.context = context;
        masterDataSet = myDataSet;
    }


    public walletHistory_Adapter.SubHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_history_item, parent, false);
        return new SubHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(SubHolder holder, int position)
    {
        holder.walletHistoryItem_amount.setText(masterDataSet.get(position).getSs());
        if(masterDataSet.get(position).getSv().equals("c")||masterDataSet.get(position).getSv().equals("C"))
        {
            holder.transactionImage.setImageResource(R.drawable.ic_added_funds);
        }
        else if(masterDataSet.get(position).getSv().equals("d")||masterDataSet.get(position).getSv().equals("D"))
        {
            holder.transactionImage.setImageResource(R.drawable.ic_payed_funds);
        }



    }

    @Override
    public int getItemCount() {
        return masterDataSet.size();
    }
}