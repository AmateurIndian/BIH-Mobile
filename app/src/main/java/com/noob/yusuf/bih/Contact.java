package com.noob.yusuf.bih;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Contact extends AppCompatActivity {

    String str;
    ListView display;
    String url = "http://www.bilkent.edu.tr/phonedir/";
    ArrayList<Person> contactList = new ArrayList<Person>();

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
            Intent in = new Intent(Contact.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else
            startActivity( new Intent(Contact.this, About.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BIH ~ Contact");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.purple))));

        AssetManager am = getAssets();
        Button btn = (Button)findViewById(R.id.btnDir);
        final TextView lbl = (TextView)findViewById(R.id.lblHead);


        try{

            InputStream is = am.open("contacts");
            BufferedReader input = new BufferedReader( (new InputStreamReader( is)));

            while ( (str = input.readLine())!= null){
                contactList.add( new Person( str, (str = input.readLine()), (str = input.readLine())));
            }
            display = (ListView)findViewById(R.id.contactDisp);

            CustomAdapter adapter = new CustomAdapter(this, contactList);
            display.setAdapter(adapter);


        }catch( IOException e){
            e.printStackTrace();
        }

        btn.setOnClickListener( new openDir());
        display.setOnItemClickListener( new select());

    }

    public class openDir implements View.OnClickListener{
        public void onClick( View v){
            if (isNetworkAvailable()){
                Intent site = new Intent(Intent.ACTION_VIEW);
                site.setData(Uri.parse(url));
                startActivity(site);
            }
            else{
                Toast.makeText(Contact.this, "Not connected to internet",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    class select implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
            Intent in = new Intent(Contact.this, PersonInfo.class);
            Person selected = contactList.get(position);
            in.putExtra("Person", selected);
            startActivity( in);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}



