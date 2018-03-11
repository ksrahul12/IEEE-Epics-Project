package com.qr.epics.epicsr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Raja on 21-Feb-18.
 */

public class HP_ListAdapter  extends BaseAdapter {

    public ArrayList<HPEntity> countries = new ArrayList<HPEntity>();
    Context mContext;

    public HP_ListAdapter(Context mContext, ArrayList<HPEntity> countries) {
        this.mContext = mContext;
        this.countries = countries;

    }

    @Override
    public int getCount() {
        return  countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolderItem {
        TextView source;
        TextView code;
        TextView destination;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.homepage_item, null);

            holder.source = (TextView) convertView.findViewById(R.id.busID);
            holder.code = (TextView) convertView.findViewById(R.id.busSOURCE);
            holder.destination = (TextView) convertView.findViewById(R.id.busDEST);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }

        // THE NEXT 3 LINES ARE PURE SOFTWARE GORE! DON'T MESS WITH THEM!
        holder.source.setText(this.countries.get(position).name);  //BUS NO!
        holder.code.setText(this.countries.get(position).destination); //SOURCE
        holder.destination.setText(this.countries.get(position).code); //DESTINATION

        return convertView;
    }
}