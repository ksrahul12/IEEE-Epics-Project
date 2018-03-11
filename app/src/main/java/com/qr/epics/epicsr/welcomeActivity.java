package com.qr.epics.epicsr;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class welcomeActivity extends AppCompatActivity {
    private static int TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(welcomeActivity.this, loginPage.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
