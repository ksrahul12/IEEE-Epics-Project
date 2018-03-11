package com.qr.epics.epicsr;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class payForTickets extends AppCompatActivity implements payTicket_JSONOP.download_complete {

    FloatingActionButton payNow,payMoney,next,back;
    TextView Paynowtext,infoTV,information; private IntentIntegrator qrScan;
    String scannedMetaData,retrievedData="";
    ImageView qrlogo;



    RadioGroup destinationList;
    RadioButton A,B,C,D,E,F,G,H,I,J;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_for_tickets);
        qrScan = new IntentIntegrator(this);


        destinationList = (RadioGroup)findViewById(R.id.radioGroup);
        payNow = (FloatingActionButton)findViewById(R.id.paynow);
        next = (FloatingActionButton)findViewById(R.id.floatingActionButton5); next.setVisibility(View.GONE);
        back = (FloatingActionButton)findViewById(R.id.floatingActionButton4); back.setVisibility(View.GONE);
        payMoney = (FloatingActionButton)findViewById(R.id.payMoney); payMoney.setVisibility(View.GONE);


        infoTV = (TextView)findViewById(R.id.textView3); infoTV.setVisibility(View.INVISIBLE);
        information=(TextView)findViewById(R.id.information);
        qrlogo = (ImageView)findViewById(R.id.imageView6);


        A = (RadioButton)findViewById(R.id.radioButton); A.setVisibility(View.INVISIBLE);
        B = (RadioButton)findViewById(R.id.radioButton2); B.setVisibility(View.INVISIBLE);
        C= (RadioButton)findViewById(R.id.radioButton3); C.setVisibility(View.INVISIBLE);
        D= (RadioButton)findViewById(R.id.radioButton4); D.setVisibility(View.INVISIBLE);
        E= (RadioButton)findViewById(R.id.radioButton5); E.setVisibility(View.INVISIBLE);
        F= (RadioButton)findViewById(R.id.radioButton6); F.setVisibility(View.INVISIBLE);
        G= (RadioButton)findViewById(R.id.radioButton7); G.setVisibility(View.INVISIBLE);
        H= (RadioButton)findViewById(R.id.radioButton8); H.setVisibility(View.INVISIBLE);
        I= (RadioButton)findViewById(R.id.radioButton9); I.setVisibility(View.INVISIBLE);
        J= (RadioButton)findViewById(R.id.radioButton10); J.setVisibility(View.INVISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  ;performDestinationSelector();
            }
        });

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qrScan.initiateScan();
            }
        });

        payMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(destinationList.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(getApplicationContext(),"Select a destination point",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"You'll be redirected to payment portal",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

   void performDestinationSelector()
    {
        infoTV.setText("Select your Destination:");
        J.setEnabled(true);
        if(A.isChecked())
        {
            A.setEnabled(false);

            B.setText(B.getText().toString()+"      ₹10");
            C.setText(C.getText().toString()+"      ₹13");
            D.setText(D.getText().toString()+"      ₹15");
            E.setText(E.getText().toString()+"      ₹17");
            F.setText(F.getText().toString()+"      ₹20");
            G.setText(G.getText().toString()+"      ₹23");
            H.setText(H.getText().toString()+"      ₹25");
            I.setText(I.getText().toString()+"      ₹27");
            J.setText(J.getText().toString()+"      ₹30");

        }
        else  if(B.isChecked())
        {
            A.setEnabled(false); B.setEnabled(false);

            C.setText(C.getText().toString()+"      ₹10");
            D.setText(D.getText().toString()+"      ₹13");
            E.setText(E.getText().toString()+"      ₹15");
            F.setText(F.getText().toString()+"      ₹17");
            G.setText(G.getText().toString()+"      ₹20");
            H.setText(H.getText().toString()+"      ₹23");
            I.setText(I.getText().toString()+"      ₹25");
            J.setText(J.getText().toString()+"      ₹27");
        }
        else  if(C.isChecked())
        {
            A.setEnabled(false); B.setEnabled(false); C.setEnabled(false);

            D.setText(D.getText().toString()+"      ₹10");
            E.setText(E.getText().toString()+"      ₹13");
            F.setText(F.getText().toString()+"      ₹15");
            G.setText(G.getText().toString()+"      ₹17");
            H.setText(H.getText().toString()+"      ₹20");
            I.setText(I.getText().toString()+"      ₹23");
            J.setText(J.getText().toString()+"      ₹25");
        }
        else if(D.isChecked())
        {
            A.setEnabled(false); B.setEnabled(false); C.setEnabled(false); D.setEnabled(false);

            E.setText(E.getText().toString()+"      ₹10");
            F.setText(F.getText().toString()+"      ₹23");
            G.setText(G.getText().toString()+"      ₹15");
            H.setText(H.getText().toString()+"      ₹17");
            I.setText(I.getText().toString()+"      ₹20");
            J.setText(J.getText().toString()+"      ₹23");
        }
        else  if(E.isChecked())
        {
            A.setEnabled(false); B.setEnabled(false); C.setEnabled(false); D.setEnabled(false);
            E.setEnabled(false);

            F.setText(F.getText().toString()+"      ₹10");
            G.setText(G.getText().toString()+"      ₹13");
            H.setText(H.getText().toString()+"      ₹15");
            I.setText(I.getText().toString()+"      ₹17");
            J.setText(J.getText().toString()+"      ₹20");
        }
        else  if(F.isChecked())
        {
            A.setEnabled(false); B.setEnabled(false); C.setEnabled(false); D.setEnabled(false);
            E.setEnabled(false); F.setEnabled(false);

            G.setText(G.getText().toString()+"      ₹10");
            H.setText(H.getText().toString()+"      ₹13");
            I.setText(I.getText().toString()+"      ₹15");
            J.setText(J.getText().toString()+"      ₹17");
        }
        else if(G.isChecked())
        {
            A.setEnabled(false); B.setEnabled(false); C.setEnabled(false); D.setEnabled(false);
            E.setEnabled(false); F.setEnabled(false);
            G.setEnabled(false);

            H.setText(H.getText().toString()+"      ₹25");
            I.setText(I.getText().toString()+"      ₹27");
            J.setText(J.getText().toString()+"      ₹30");
        }
        else  if(H.isChecked())
        {
            A.setEnabled(false); B.setEnabled(false); C.setEnabled(false); D.setEnabled(false);
            E.setEnabled(false); F.setEnabled(false);  G.setEnabled(false); H.setEnabled(false);
            I.setText(I.getText().toString()+"      ₹10");
            J.setText(J.getText().toString()+"      ₹13");
        }
        else  if(I.isChecked())
        {
            A.setEnabled(false); B.setEnabled(false); C.setEnabled(false); D.setEnabled(false);
            E.setEnabled(false); F.setEnabled(false);  G.setEnabled(false); H.setEnabled(false);
            I.setEnabled(false);

            J.setText(J.getText().toString()+"      ₹10");
        }

        destinationList.clearCheck();
        payMoney.setVisibility(View.VISIBLE);
        next.setVisibility(View.GONE);

    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try
                {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    Paynowtext.setText(obj.getString("name"));
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    //Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    retrievedData = result.getContents();
                    retrieveDBData();


                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void retrieveDBData()
    {
        information.setVisibility(View.GONE);
        qrlogo.setVisibility(View.GONE);
        payTicket_JSONOP download_data = new payTicket_JSONOP((payTicket_JSONOP.download_complete) this);
        download_data.download_data_from_link("http://nazgul.in/MyApi/qrdata.php?qrmeta="+retrievedData);
    }


    @Override
    public void getActualdata(String data)
    {
        try {

                JSONArray data_array=new JSONArray(data);

                for (int i = 0 ; i < data_array.length() ; i++)
                {
                    JSONObject obj=new JSONObject(data_array.get(i).toString());


                    if(i==0){  A.setText(obj.getString("A")); next.setVisibility(View.VISIBLE); back.setVisibility(View.VISIBLE );infoTV.setVisibility(View.VISIBLE); A.setVisibility(View.VISIBLE); }
                    if(i==0) { B.setText(obj.getString("B"));  B.setVisibility(View.VISIBLE); }
                    if(i==0)  {C.setText( obj.getString("C")); C.setVisibility(View.VISIBLE); }
                    if(i==0)  {D.setText (obj.getString("D")); D.setVisibility(View.VISIBLE); }
                    if(i==0)  {E.setText(obj.getString("E")); E.setVisibility(View.VISIBLE); }
                    if(i==0)  {F.setText( obj.getString("F")); F.setVisibility(View.VISIBLE); }
                    if(i==0)  {G.setText( obj.getString("G")); G.setVisibility(View.VISIBLE); }
                    if(i==0)  {H.setText (obj.getString("H")); H.setVisibility(View.VISIBLE); }
                    if(i==0)  {I.setText( obj.getString("I")); I.setVisibility(View.VISIBLE); }
                    if(i==0)  {J.setText( obj.getString("J")); J.setVisibility(View.VISIBLE); J.setEnabled(false);}

                }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
