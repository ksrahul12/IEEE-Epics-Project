package com.qr.epics.epicsr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class walletPage extends AppCompatActivity      {




    ImageButton addMoney; Button add50, add100, add200, historyWallet;
     boolean open = false;

    private final String walletHistoryItem_list[] = {
            "500",
            "20",
            "17",
            "100",
            "25",
            "20",
            "17",
            "150",
            "15",
            "24",
            "19",
            "32",
            "11"

    };

    private final String walletHistoryItem_descs[] = {
            "c",
            "d",
            "d",
            "c",
            "d",
            "d",
            "d",
            "c",
            "d",
            "d",
            "d",
            "d",
            "c"


    };


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_page);
        setTitle("Wallet");


//------------------------------------------------------------------------------------------------------
     /*  RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.walletHistoryList);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<walletHistoryItems> itemList = preparePrimaryData();
        ArrayList<walletHistoryItems> itemListDesc = prepareSecondaryData();


        walletHistory_Adapter adapter= new walletHistory_Adapter(getApplicationContext(),itemList);
        mRecyclerView.setAdapter(adapter);  */

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        addMoney = (ImageButton)findViewById(R.id.addMoney);
        add50 = (Button)findViewById(R.id.add50);
        add100 = (Button)findViewById(R.id.add100);
        add200 = (Button)findViewById(R.id.add200);


        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        add50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        add100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        add200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

/*    private ArrayList<walletHistoryItems> preparePrimaryData(){

        ArrayList<walletHistoryItems> sub = new ArrayList<>();
        for(int i=0;i<walletHistoryItem_list.length;i++){
            walletHistoryItems historyItemObj = new walletHistoryItems(walletHistoryItem_list[i],walletHistoryItem_descs[i]);
            sub.add(historyItemObj);
        }
        return sub;
    }

    private ArrayList<walletHistoryItems> prepareSecondaryData(){

        ArrayList<walletHistoryItems> sub = new ArrayList<>();
        for(int i=0;i<walletHistoryItem_descs.length;i++){
            walletHistoryItems historyItemObj = new walletHistoryItems(walletHistoryItem_descs[i],walletHistoryItem_list[i]);
            sub.add(historyItemObj);
        }
        return sub;
    }

    public  void toWalletHistory()
    {
        Intent toWalletHistoryPage = new Intent(walletPage.this,DeadEnd.class);
        startActivity(toWalletHistoryPage);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    } */

}