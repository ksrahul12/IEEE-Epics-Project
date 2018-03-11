package com.qr.epics.epicsr;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

import static com.qr.epics.epicsr.loginPage.USER_META;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static boolean mTwoPane;

    private DrawerLayout drawer;
    public Toolbar toolBar;
    FloatingActionButton payForThemTickets;

    ConstraintLayout currentTicketLayout;
    Fragment fragment = null;
    //SharedPreferences.Editor editor = getSharedPreferences(USER_META, MODE_PRIVATE).edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("E-RTC");



         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Setting up Homepage as default fragment
        navigationView.getMenu().getItem(0);

        payForThemTickets = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        payForThemTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(MainActivity.this, payForTickets.class);
                startActivity(x);
            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent navDrawerAction;

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        payForThemTickets = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        if (id == R.id.active_ticket)
        {
            navigationView.getMenu().getItem(0).setChecked(true);

            fragment = new activeTickets();
            payForThemTickets.show();



        }
        else if (id == R.id.nav_payfortickets)
        {
            navigationView.getMenu().getItem(1).setChecked(true);

            fragment = new homePageFrag();
          payForThemTickets.show();

            //Intent x = new Intent(MainActivity.this,HomePage.class); startActivity(x);

        }
        else if (id == R.id.nav_wallet)
        {
            navigationView.getMenu().getItem(2).setChecked(true);

            fragment = new WalletFragmentPage();
            payForThemTickets.hide();

        }

         else if (id == R.id.nav_profile)
         {
            navigationView.getMenu().getItem(3).setChecked(true);

             fragment = new profile();
             payForThemTickets.hide();
        }
        else if (id == R.id.nav_settings)
        {
            navigationView.getMenu().getItem(4).setChecked(true);

            fragment = new deadfrag();
            payForThemTickets.hide();
        }
        else if (id == R.id.nav_logout)
        {
            navigationView.getMenu().getItem(5).setChecked(true);
            final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("LogOut");
            alert.setMessage("Are you sure?");



            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                   // editor.clear();
                  //  editor.commit();
                    Intent exit = new Intent(MainActivity.this, loginPage.class);
                    startActivity(exit);
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    dialog.dismiss();
                }
            });

            alert.show();
            navigationView.getMenu().getItem(5).setChecked(false);

        }


        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
