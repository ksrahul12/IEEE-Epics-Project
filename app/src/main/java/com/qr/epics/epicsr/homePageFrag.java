package com.qr.epics.epicsr;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Raja on 29-Jan-18.
 */

public class homePageFrag extends Fragment implements HP_JSON_Download.download_complete {

    Context applicationContext;

    public ListView list;
    public ArrayList<HPEntity> countries = new ArrayList<HPEntity>();
    public HP_ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View view = inflater.inflate(R.layout.homepage_lv, container, false);


        list = (ListView) view.findViewById(R.id.homepageListView);
        adapter = new HP_ListAdapter(getActivity(), countries);
        list.setAdapter(adapter);



        HP_JSON_Download download_data = new HP_JSON_Download((HP_JSON_Download.download_complete) this);
        download_data.download_data_from_link("http://www.nazgul.in/MyApi/Api.php");

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("E-RTC");


    }
    public void get_data(String data)
    {


        try {
            JSONArray data_array=new JSONArray(data);

            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                HPEntity add=new HPEntity();
                add.name = obj.getString("busno");
                add.code = obj.getString("destination");
                add.destination = obj.getString("source"); //actually destination

                countries.add(add);
            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public Context getApplicationContext() {

        return applicationContext;
    }
}
