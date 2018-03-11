package com.qr.epics.epicsr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Raja on 22-Feb-18.
 */

public class WP_ListAdapter  extends BaseAdapter {
    public ArrayList<WPEntity> transacItemDetails = new ArrayList<WPEntity>();
    Context mContext;

    public WP_ListAdapter(Context mContext, ArrayList<WPEntity> transacItemDetails) {
        this.mContext = mContext;
        this.transacItemDetails = transacItemDetails;
    }

    @Override
    public int getCount() {
        return  transacItemDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return transacItemDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolderItem {
        TextView bank;
        TextView amount;
        TextView date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.wallet_history_item, null);

            holder.bank = (TextView) convertView.findViewById(R.id.infoDetails);
            holder.amount = (TextView) convertView.findViewById(R.id.walletHistoryItemAmount);
            holder.date = (TextView) convertView.findViewById(R.id.walletHistoryItemDate);


            convertView.setTag(holder);
        } else {
            holder = (WP_ListAdapter.ViewHolderItem) convertView.getTag();
        }

        holder.bank.setText(this.transacItemDetails.get(position).bank);
        holder.amount.setText(this.transacItemDetails.get(position).amount);
        holder.date.setText(this.transacItemDetails.get(position).date);

        return convertView;
    }
}