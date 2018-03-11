package com.qr.epics.epicsr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DeadEnd extends AppCompatActivity {
    walletPage x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dead_end);

        x = new walletPage(); x.open = false;
    }
}
