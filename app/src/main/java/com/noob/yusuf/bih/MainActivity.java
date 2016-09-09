package com.noob.yusuf.bih;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String url = "https://bilkentinternational.net/";
    LinearLayout registration;
    LinearLayout dorm;
    LinearLayout bih;
    LinearLayout bilLife;
    LinearLayout utilities;
    LinearLayout contact;
    LinearLayout title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (LinearLayout)findViewById(R.id.mainTitle);

        registration = (LinearLayout)findViewById(R.id.reg);
        registration.setOnClickListener( new regActivity());

        dorm = (LinearLayout) findViewById(R.id.dorm);
        dorm.setOnClickListener( new dormActivity());

        bih = (LinearLayout)findViewById(R.id.bih);
        bih.setOnClickListener( new siteOpen());


        bilLife = (LinearLayout) findViewById(R.id.life);
        bilLife.setOnClickListener( new lifeActivity());

        utilities = (LinearLayout)findViewById(R.id.utilities);
        utilities.setOnClickListener( new utilActivity());

        contact = (LinearLayout) findViewById(R.id.contact);
        contact.setOnClickListener( new contactActivity());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabInfo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, About.class);
                startActivity(in);
            }
        });
    }

    class lifeActivity implements View.OnClickListener {

        public void onClick(View v) {
            final Intent page = new Intent(MainActivity.this, CampusLife.class);
            startActivity(page);

        }

    }

    class siteOpen implements View.OnClickListener{

        public void onClick(View v){

            if (isNetworkAvailable()){
                Intent site = new Intent(Intent.ACTION_VIEW);
                site.setData(Uri.parse(url));
                startActivity(site);
            }
            else{
                Toast.makeText(MainActivity.this, "Not connected to internet",
                        Toast.LENGTH_LONG).show();
            }


        }
    }
    class dormActivity implements View.OnClickListener {

        public void onClick(View v) {
            final Intent page = new Intent(MainActivity.this, DormMain.class);
            startActivity(page);

        }
    }

    class contactActivity implements View.OnClickListener {

        public void onClick(View v) {
            Intent page = new Intent(MainActivity.this, Contact.class);
            page.putExtra("color","#c5218e");
            startActivity(page);
        }

    }

    class utilActivity implements View.OnClickListener {

        public void onClick(View v) {
            Intent page = new Intent(MainActivity.this, UtilityMenu.class);
            startActivity(page);
        }

    }

    class regActivity implements View.OnClickListener {

        public void onClick(View v) {
            Intent page = new Intent(MainActivity.this, RegInfo.class);
            startActivity(page);
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
