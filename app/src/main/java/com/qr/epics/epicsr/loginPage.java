package com.qr.epics.epicsr;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class loginPage extends MainActivity implements Alpha_JSON_Downloader.download_complete {

    EditText logET,pwdET;
    TextView signup,forgotpwd;
    CardView buttonLogin;
    RelativeLayout container;
    AnimationDrawable anim;
    String idC="",pwdC="";
    ProgressBar loadingPG;
    ImageView loadingBG;
    Boolean passwordRetrival=false;

    //ALPHA SHARED PREFERENCE VARIABLE - DO NOT CHANGE!
    public static final String USER_META = "USER_META";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loadingBG = (ImageView)findViewById(R.id.loadingBGLoginPage); loadingBG.setVisibility(View.GONE);
        loadingPG = (ProgressBar)findViewById(R.id.loadingPBLoginPage); loadingPG.setVisibility(View.GONE);

        logET = (EditText) findViewById(R.id.loginEditText);
        pwdET = (EditText) findViewById(R.id.passwordEditText);
        signup = (TextView) findViewById(R.id.signUp);
        forgotpwd = (TextView) findViewById(R.id.forgotPwd);
        buttonLogin = (CardView)findViewById(R.id.loginButtonCardView);
        container = (RelativeLayout)findViewById(R.id.container);
        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(2000);
        anim.setExitFadeDuration(2000);
         final Alpha_JSON_Downloader download_data = new Alpha_JSON_Downloader((Alpha_JSON_Downloader.download_complete) this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                loadingBG.setVisibility(View.VISIBLE); loadingPG.setVisibility(View.VISIBLE);
               // Toast.makeText(getApplicationContext(),logET.getText().toString(),Toast.LENGTH_SHORT).show();
            if(logET.getText().toString().equals("")||logET.getText().toString().equals(" ")||pwdET.getText().toString().equals("")||pwdET.getText().toString().equals(" "))
            {
                logET.setError("Field cannot be empty"); pwdET.setError("Field cannot be empty");
            }
            else
            {
                download_data.download_data_from_link("http://nazgul.in/MyApi/logincontrol.php?meta="+logET.getText().toString());


                if( idC==logET.getText().toString())
                {
                    Intent toHomePage = new Intent(loginPage.this, MainActivity.class);
                    startActivity(toHomePage);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

            }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRegistration = new Intent(loginPage.this,signUp.class);
                startActivity(toRegistration);
            }
        });

        forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(loginPage.this);
                final EditText edittext = new EditText(getApplicationContext());
                alert.setMessage("Enter Your UserID:");
                alert.setTitle("Forgot Password?");

                alert.setView(edittext);

                alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value

                        String passwordRet = edittext.getText().toString();
                        passwordRetrival=true;
                        download_data.download_data_from_link("http://nazgul.in/MyApi/logincontrol.php?meta="+passwordRet);
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });




    }
    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }


    //JSON Downloader for AUTHENTICATION
    @Override
    public void get_data(String data)
    {
        try {
            idC=""; pwdC="";
            JSONArray data_array=new JSONArray(data);

            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                idC = idC + obj.getString("userid").toString();
                pwdC = pwdC + obj.getString("userpwd").toString();
                Log.d("x",idC);
                Log.d("y",pwdC);


            }
        if(passwordRetrival==false)
        {
            if (logET.getText().toString().equals(idC) && pwdET.getText().toString().equals(pwdC))
            {
                loadingBG.setVisibility(View.GONE);
                loadingPG.setVisibility(View.GONE);

                SharedPreferences.Editor editor = getSharedPreferences(USER_META, MODE_PRIVATE).edit();
                editor.putString("userMetaID", idC);
                editor.apply();

                Intent toHomePage = new Intent(loginPage.this, MainActivity.class);
                startActivity(toHomePage);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else {
                loadingPG.setVisibility(View.GONE);
                loadingBG.setVisibility(View.GONE);
                if (idC.equals(logET.getText().toString()) || pwdC.equals(pwdET.getText().toString())) {
                    logET.setError("Invalid Credential");
                    pwdET.setError("Invalid Credential");
                }
                //Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Password: "+pwdC+"\n\nThis is just a demo!\nPassword is sent via SMS in Final Product",Toast.LENGTH_LONG).show();
            passwordRetrival=false;
        }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

