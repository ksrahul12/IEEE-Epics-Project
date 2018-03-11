package com.qr.epics.epicsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.qr.epics.epicsr.loginPage.USER_META;

public class paymentCards extends AppCompatActivity implements Alpha_JSON_Downloader.download_complete {

    public ListView list;
    public ArrayList<cardEntity> items = new ArrayList<cardEntity>();
    public cards_ListAdapter adapter;

    CardView addCard ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_cards);

        list = (ListView) findViewById(R.id.savedCardsLV);
        adapter = new cards_ListAdapter(this, items);
        list.setAdapter(adapter);

        addCard = (CardView)findViewById(R.id.addcards);
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddNewCard = new Intent(paymentCards.this,addNewCard.class);
                startActivity(toAddNewCard);
            }
        });


        SharedPreferences prefs = getSharedPreferences(USER_META,0);
        String userID=prefs.getString("userMetaID", null);

        Alpha_JSON_Downloader download_data = new Alpha_JSON_Downloader((Alpha_JSON_Downloader.download_complete) this);
        download_data.download_data_from_link("http://nazgul.in/MyApi/paymentinfo.php?useridx="+userID);
    }

    @Override
    public void get_data(String data) {

        try {
            JSONArray data_array = new JSONArray(data);
            String temp="",tempx="";

            for (int i = 0; i < data_array.length(); i++) {
                JSONObject obj = new JSONObject(data_array.get(i).toString());
                temp=""; tempx="";
                cardEntity add = new cardEntity();
                tempx = obj.getString("cardno");
                temp = temp + tempx.charAt(0) +  tempx.charAt(1) +  tempx.charAt(2) + tempx.charAt(3) +"-XXXX-XXXX-" + tempx.charAt(0)
                        +  tempx.charAt(12) +  tempx.charAt(13) +tempx.charAt(14) + tempx.charAt(15);
                add.cardnumber = temp;


                items.add(add);
            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
