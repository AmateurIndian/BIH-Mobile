package com.noob.yusuf.bih;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class LocationList extends AppCompatActivity {

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
            Intent in = new Intent(LocationList.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else
            startActivity( new Intent(LocationList.this, About.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BIH ~ Bilkent Map");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.blue))));


        EditText search = (EditText)findViewById(R.id.txtSearch);
        final ListView locDisp = (ListView)findViewById(R.id.locDisp);
        final ArrayList <String> dispList = new ArrayList<>();
        String str, delim = ",";
        AssetManager am = getAssets();
        StringTokenizer st;
        final ArrayList<Site> list = new ArrayList<Site>();


        try{
            InputStream is = am.open("loc.txt");
            BufferedReader input = new BufferedReader( new InputStreamReader(is));
            Site currSite;
            while ((str = input.readLine()) != null){
                st = new StringTokenizer(str, delim);
                currSite = new Site(st.nextElement().toString(), st.nextElement().toString(), st.nextElement().toString(), st.nextElement().toString());
                list.add( currSite);
                if(!currSite.name.equals("DEFAULT"))
                    dispList.add(currSite.title);
            }
            Collections.sort(dispList);
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dispList);
            locDisp.setAdapter(adapter);

            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                    locDisp.setAdapter(adapter);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            locDisp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                String selectedName;
                Intent in = new Intent(LocationList.this, MapsUtil.class);

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String filteredItem = (String) adapter.getItem(position);

                    for(Site site: list){
                        if( site.title.equals(filteredItem)){
                            in.putExtra("Site", site);
                            break;
                        }
                    }
                    startActivity(in);
                }
            });



        }catch ( IOException e){
            e.printStackTrace();
        }

    }
}
