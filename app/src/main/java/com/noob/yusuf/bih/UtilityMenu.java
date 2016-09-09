package com.noob.yusuf.bih;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class UtilityMenu extends AppCompatActivity {
    LinearLayout map, translate, bus, currency, blank1, blank2;

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
            Intent in = new Intent(UtilityMenu.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else
            startActivity( new Intent(UtilityMenu.this, About.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_menu);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.yellow))));
        getSupportActionBar().setTitle("BIH ~ Utilities");


        map = (LinearLayout)findViewById(R.id.map);
        map.setOnClickListener( new mapOption());

        translate = (LinearLayout)findViewById(R.id.translator);
        translate.setOnClickListener( new translateOption());

        bus = (LinearLayout) findViewById(R.id.bus);
        bus.setOnClickListener( new busOption());

        currency = (LinearLayout) findViewById(R.id.convertor);
        currency.setOnClickListener( new convertorOption());

    }

    class translateOption  implements View.OnClickListener {

        public void onClick(View v) {
            Intent page = new Intent(UtilityMenu.this, TranslatorUtil.class);
            page.putExtra("color", getResources().getString(R.string.red));
            startActivity(page);
        }
    }

    class mapOption  implements View.OnClickListener {

        public void onClick(View v) {
            Intent page = new Intent(UtilityMenu.this, LocationList.class);
            page.putExtra("color", getResources().getString(R.string.blue));
            startActivity(page);
        }
    }
    class convertorOption  implements View.OnClickListener {

        public void onClick(View v) {
            Intent page = new Intent(UtilityMenu.this, ConvertorUtil.class);
            page.putExtra("color", getResources().getString(R.string.green));
            startActivity(page);
        }
    }

    class busOption implements View.OnClickListener {

        public void onClick(View v) {
            final Intent page = new Intent(UtilityMenu.this, BusMenu.class);
            startActivity(page);

        }
    }


}


