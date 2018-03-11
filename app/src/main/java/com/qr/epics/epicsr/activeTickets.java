package com.qr.epics.epicsr;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;
import static com.qr.epics.epicsr.loginPage.USER_META;

public class activeTickets extends Fragment implements Alpha_JSON_Downloader.download_complete {


    CountDownTimer newtimer;   TextView liveclock, source,destination;
    String destinationS="",sourceS="",dateS="",timeS="",currentTime="";
    ImageView statusColor;

    ConstraintLayout nothinghere,CurrentTicketLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_active_tickets, container, false);

        SharedPreferences prefs = getActivity().getSharedPreferences(USER_META,0);
        String userID=prefs.getString("userMetaID", null);
        //Log.d("xxx",userID);
        Alpha_JSON_Downloader download_data = new Alpha_JSON_Downloader((Alpha_JSON_Downloader.download_complete) this);
        download_data.download_data_from_link("http://www.nazgul.in/MyApi/activeticket.php?idx="+userID);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Active Ticket");
        statusColor = (ImageView)view.findViewById(R.id.imageView10);
        nothinghere = (ConstraintLayout)view.findViewById(R.id.nothinghere);
        CurrentTicketLayout = (ConstraintLayout)view.findViewById(R.id.CurrentTicketLayout);
        source = (TextView)view.findViewById(R.id.homepageFrom);
        destination = (TextView)view.findViewById(R.id.homepageTO);

        if(!sourceS.equals(null))
        {
            nothinghere.setVisibility(View.GONE);
        }
        else
        {
            nothinghere.setVisibility(View.VISIBLE);
        }

        //------------------------------------------------------------------------------------------LIVE CLOCK-------------
        liveclock = (TextView)view.findViewById(R.id.liveClock);
        newtimer = new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                Calendar c = Calendar.getInstance();
                liveclock.setText(c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
            }
            public void onFinish() {

            }
        };
        newtimer.start();
        //-----------------------------------------------------------------------------------------------------------------


    }

    @Override
    public void get_data(String data)
    {
        try {
             destinationS="";sourceS="";dateS="";timeS=""; currentTime="";

            JSONArray data_array=new JSONArray(data);

            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                sourceS = sourceS + obj.getString("source").toString();
                destinationS = destinationS + obj.getString("destination").toString();
                dateS = dateS+ obj.getString("date").toString();
                timeS = timeS + obj.getString("time").toString();


            }
            source.setText(sourceS);
            destination.setText(destinationS);
            int currentTime = new Time(System.currentTimeMillis()).getHours();
            int timeSI = new Integer(timeS);

            if(currentTime>timeSI)
            {
               if(currentTime-timeSI>3)
                statusColor.setImageResource(R.color.red);
               else if(currentTime>timeSI)
                statusColor.setImageResource(R.color.orange);

            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
