package com.noob.yusuf.bih;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class BusMenu extends AppCompatActivity {

    Button btnRoutes;
    LinearLayout mainCampus;
    LinearLayout eastCampus;

    @Override
    public  boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            Intent in = new Intent(BusMenu.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else
            startActivity( new Intent(BusMenu.this, About.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_menu);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.purple))));
        getSupportActionBar().setTitle("BIH ~ Bus Schedule");

        btnRoutes = (Button)findViewById(R.id.btnRoutes);

        mainCampus = (LinearLayout)findViewById(R.id.mainCampus);
        eastCampus = (LinearLayout)findViewById(R.id.eastCampus);

        mainCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BusMenu.this, ScheduleCity.class);
                in.putExtra("Site", "http://www.bilkent.edu.tr/bilkent/admin-unit/transport/merkez_cizelge.htm");
                in.putExtra("Color", getResources().getString(R.string.skyBlue));
                in.putExtra("StopFile", "");
                in.putExtra("Type", 0);
                startActivity(in);
            }
        });

        eastCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(BusMenu.this, ScheduleCity.class);
                in.putExtra("Site", "http://www.bilkent.edu.tr/bilkent/admin-unit/transport/dogu_cizelge.htm");
                in.putExtra("Color", getResources().getString(R.string.yellow));
                in.putExtra("StopFile", "");
                in.putExtra("Type", 1);
                startActivity(in);
            }
        });

        btnRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent site = new Intent(Intent.ACTION_VIEW);
                site.setData(Uri.parse("http://w3.bilkent.edu.tr/bilkent/transportation/"));
                startActivity(site);
            }
        });

    }
}
