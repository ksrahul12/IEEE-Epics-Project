package com.qr.epics.epicsr;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class signUp extends AppCompatActivity implements Alpha_JSON_Downloader.download_complete{

    CheckBox condition; Button submitRegistraion;
    TextView name,email,password,confirmP,phone;

    String idS="",emailS="",passwordS="",confirmPS="",phoneS="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Registration for E-RTC App");

        condition = (CheckBox)findViewById(R.id.conditions);
        submitRegistraion = (Button)findViewById(R.id.submitRegistraion);
        submitRegistraion.setEnabled(false);

        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        phone = (TextView)findViewById(R.id.phone);
        password = (TextView)findViewById(R.id.password);
        confirmP = (TextView)findViewById(R.id.cpassword);
        final Alpha_JSON_Downloader download_data = new Alpha_JSON_Downloader((Alpha_JSON_Downloader.download_complete) this);

        condition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(condition.isChecked()) {
                   if (!name.getText().toString().equals("") && !phone.getText().toString().equals("")&& !password.getText().toString().equals("") && !confirmP.getText().toString().equals(""))
                   {
                       if (!name.getText().toString().equals(" ") && !phone.getText().toString().equals(" ") && !email.getText().toString().equals(" ") && !password.getText().toString().equals(" ") || confirmP.getText().toString().equals(" "))
                       {
                           if (password.getText().toString().equals(confirmP.getText().toString()))
                           {
                               submitRegistraion.setEnabled(true);
                           }
                           else {
                               Toast.makeText(getApplicationContext(), "Passwords does not match!!", Toast.LENGTH_SHORT).show();
                               submitRegistraion.setEnabled(false);
                               condition.toggle();
                           }
                       }
                       else {
                           Toast.makeText(getApplicationContext(), "Enter valid details!", Toast.LENGTH_SHORT).show();
                           submitRegistraion.setEnabled(false);
                           condition.toggle();
                       }
                   }
                   else {
                       Toast.makeText(getApplicationContext(), "Enter details!", Toast.LENGTH_SHORT).show();
                       submitRegistraion.setEnabled(false);
                       condition.toggle();
                   }
               }
            }
        });

        submitRegistraion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download_data.download_data_from_link("http://nazgul.in/MyApi/register.php?idx="+name.getText().toString()+"&pwdx="+password.getText().toString()+"&phnx="+phone.getText().toString());

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void get_data(String data)
    {
        try {
            idS="";emailS="";passwordS="";confirmPS="";phoneS="";

            JSONArray data_array=new JSONArray(data);

            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                idS = idS + obj.getString("userid").toString();
                phoneS = phoneS + obj.getString("phone").toString();
            }

            final AlertDialog.Builder alert = new AlertDialog.Builder(signUp.this);
            alert.setTitle("Registration Successful!");
            alert.setMessage("Phone "+phoneS+" is registered with ID: "+idS);

            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //What ever you want to do with the value
                    dialog.dismiss();
                }
            });
            alert.show();

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
