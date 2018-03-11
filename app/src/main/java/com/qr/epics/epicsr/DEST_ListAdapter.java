package com.qr.epics.epicsr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Raja on 02-Mar-18.
 */

public class DEST_ListAdapter extends BaseAdapter {

    public ArrayList<destinationEntities> destinations = new ArrayList<destinationEntities>();
    Context mContext;

    int count=1;

    public DEST_ListAdapter(Context mContext, ArrayList<destinationEntities> destinations) {
        this.mContext = mContext;
        this.destinations = destinations;

    }

    @Override
    public int getCount() {
        return  destinations.size();
    }

    @Override
    public Object getItem(int position) {
        return destinations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolderItem {
        TextView destinationName;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.destinations_layout_item, null);

            holder.destinationName = (TextView) convertView.findViewById(R.id.destinationName);


            convertView.setTag(holder);
        } else {
            holder = (DEST_ListAdapter.ViewHolderItem) convertView.getTag();
        }

        // THE NEXT 3 LINES ARE PURE SOFTWARE GORE! DON'T MESS WITH THEM!
        if(count==1){  holder.destinationName.setText(this.destinations.get(position).A); count++; }
        if(count==2){  holder.destinationName.setText(this.destinations.get(position).B); count++; }
        if(count==3){  holder.destinationName.setText(this.destinations.get(position).C); count++; }
        if(count==4){  holder.destinationName.setText(this.destinations.get(position).D); count++; }
        if(count==5){  holder.destinationName.setText(this.destinations.get(position).E); count++; }
        if(count==6){  holder.destinationName.setText(this.destinations.get(position).F); count++; }
        if(count==7){  holder.destinationName.setText(this.destinations.get(position).G); count++; }
        if(count==8){  holder.destinationName.setText(this.destinations.get(position).H); count++; }
        if(count==9){  holder.destinationName.setText(this.destinations.get(position).I); count++; }
        if(count==10){ holder.destinationName.setText(this.destinations.get(position).J);         }



        return convertView;
    }
}
