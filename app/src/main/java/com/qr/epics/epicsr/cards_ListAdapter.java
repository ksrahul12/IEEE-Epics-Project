package com.qr.epics.epicsr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Raja on 11-Mar-18.
 */

public class cards_ListAdapter extends BaseAdapter {

    public ArrayList<cardEntity> items = new ArrayList<cardEntity>();
    Context mContext;

    public cards_ListAdapter(Context mContext, ArrayList<cardEntity> items) {
        this.mContext = mContext;
        this.items = items;

    }

    @Override
    public int getCount() {
        return  items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolderItem {
        TextView cardno;
        ImageView cardType;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        cards_ListAdapter.ViewHolderItem holder = new cards_ListAdapter.ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cardlv_item, null);

            holder.cardno = (TextView) convertView.findViewById(R.id.cardno);
            holder.cardType = (ImageView) convertView.findViewById(R.id.cardpicture);


            convertView.setTag(holder);
        } else {
            holder = (cards_ListAdapter.ViewHolderItem) convertView.getTag();
        }

        holder.cardno.setText(this.items.get(position).cardnumber);
        if(this.items.get(position).cardnumber.charAt(0)=='4')
        {
            holder.cardType.setImageResource(R.drawable.ic_visa);
        }
        else if(this.items.get(position).cardnumber.charAt(0)=='5')
        {
            holder.cardType.setImageResource(R.drawable.ic_mastercard);
        }


        return convertView;
    }
}