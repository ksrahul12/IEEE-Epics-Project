package com.qr.epics.epicsr;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.qr.epics.epicsr.loginPage.USER_META;

public class profile extends Fragment implements Alpha_JSON_Downloader.download_complete{
    View view;

    TextView Name,Phone;
    String nameS,phoneS;

    CardView paymentMethods;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        view = inflater.inflate(R.layout.activity_profile, container, false);
        Name = (TextView)view.findViewById(R.id.nameInProfile);
        Phone = (TextView)view.findViewById(R.id.phoneInProfile);

        paymentMethods = (CardView)view.findViewById(R.id.paymentscardview);

        SharedPreferences prefs = getActivity().getSharedPreferences(USER_META,0);
        String userID=prefs.getString("userMetaID", null);


        Alpha_JSON_Downloader download_data = new Alpha_JSON_Downloader((Alpha_JSON_Downloader.download_complete) this);
        download_data.download_data_from_link("http://www.nazgul.in/MyApi/profile.php?meta="+userID);

        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Profile");

        paymentMethods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPayments = new Intent(getActivity(),paymentCards.class); startActivity(toPayments);
            }
        });
    }


    @Override
    public void get_data(String data)
    {
        try {
            nameS="";phoneS="";

            JSONArray data_array=new JSONArray(data);

            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                nameS = nameS + obj.getString("userid").toString();
                phoneS = phoneS + obj.getString("phone").toString();
            }
        Name.setText(nameS); Phone.setText(phoneS);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
