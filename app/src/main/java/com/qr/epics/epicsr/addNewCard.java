package com.qr.epics.epicsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import static com.qr.epics.epicsr.loginPage.USER_META;

public class addNewCard extends AppCompatActivity implements Alpha_JSON_Downloader.download_complete {

    EditText cardno,name,expiry,cvv;
    TextView cardTV,expiryTV,nameTV;
    ImageView type,loadingBG;
    ProgressBar loadingPG;
    Button submit;       String card="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);
        setTitle("Adding New Card");

        final Alpha_JSON_Downloader download_data = new Alpha_JSON_Downloader((Alpha_JSON_Downloader.download_complete) this);

        cardno = (EditText)findViewById(R.id.cardnoIN);
        name = (EditText)findViewById(R.id.nameIN); name.setEnabled(false);
        expiry = (EditText)findViewById(R.id.expiryIN); expiry.setEnabled(false);
        cvv = (EditText)findViewById(R.id.CVV); cvv.setEnabled(false);
        cardTV = (TextView)findViewById(R.id.textView5);
        expiryTV = (TextView)findViewById(R.id.textView7);
        nameTV = (TextView)findViewById(R.id.textView6);
        submit = (Button)findViewById(R.id.submitCard); submit.setEnabled(false);
        type = (ImageView)findViewById(R.id.cardtypeimage); type.setVisibility(View.GONE);

        loadingBG = (ImageView) findViewById(R.id.loadingBGcardPage);
        loadingPG = (ProgressBar)findViewById(R.id.progressBar3); loadingPG.setVisibility(View.GONE); loadingBG.setVisibility(View.GONE);

        SharedPreferences prefs = getSharedPreferences(USER_META,0);
        final String userID=prefs.getString("userMetaID", null);

        cardno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(cardno.getText().toString().isEmpty())
                {
                    type.setVisibility(View.GONE);
                }
                else if(cardno.getText().toString().charAt(0)=='4')
                {
                    type.setImageResource(R.drawable.ic_visa); type.setVisibility(View.VISIBLE);
                }
                else if(cardno.getText().toString().charAt(0)=='5')
                {
                    type.setImageResource(R.drawable.ic_mastercard); type.setVisibility(View.VISIBLE);
                }
                //-------------------------------------------------
                if(!cardno.getText().toString().isEmpty())
                {
                    cardTV.setText(cardno.getText().toString());
                    if(cardno.getText().toString().length()==16)
                        name.setEnabled(true);
                    else name.setEnabled(false);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!name.getText().toString().isEmpty())
                {
                    nameTV.setText(name.getText().toString());
                }
                if(cardno.getText().toString().length()==16&&!name.getText().toString().isEmpty())
                {
                    expiry.setEnabled(true);
                }
                else
                    expiry.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        expiry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!expiry.getText().toString().isEmpty())
                {
                    expiryTV.setText(expiry.getText().toString());
                }
                if(cardno.getText().toString().length()==16&&!name.getText().toString().isEmpty()&&!expiry.getText().toString().isEmpty())
                {
                    cvv.setEnabled(true); submit.setEnabled(true);
                }
                else
                {  cvv.setEnabled(false); submit.setEnabled(false); }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingBG.setVisibility(View.VISIBLE); loadingPG.setVisibility(View.VISIBLE);
                download_data.download_data_from_link("http://nazgul.in/MyApi/newcard.php?idx="+userID+"&cardx="+cardno.getText().toString()+"&cvvx="+cvv.getText().toString()+"&expx="+expiry.getText().toString());


            }
        });
    }

    @Override
    public void get_data(String data) {

        try {
            card="";
            JSONArray data_array = new JSONArray(data);
            for (int i = 0; i < data_array.length(); i++)
                {
                    JSONObject obj = new JSONObject(data_array.get(i).toString());
                    card = card + obj.getString("cardno").toString();

                }
            Log.d("x",card);
            if(card.equals(cardno.getText().toString()))
            {
                loadingBG.setVisibility(View.GONE); loadingPG.setVisibility(View.GONE);
                Intent toAddNewCard = new Intent(addNewCard.this,paymentCards.class);
                startActivity(toAddNewCard);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

