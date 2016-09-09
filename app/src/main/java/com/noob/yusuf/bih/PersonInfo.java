package com.noob.yusuf.bih;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PersonInfo extends AppCompatActivity {

    @Override
    public  boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            startActivity( new Intent(PersonInfo.this, MainActivity.class));
        else
            startActivity( new Intent(PersonInfo.this, About.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BIH ~ Contact");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.purple))));


        String fileName, str, statmentDisplay = "";

        Bundle data = getIntent().getExtras();
        final Person person = (Person)data.getParcelable("Person");

        fileName = person.file;
        Log.d("perFile", person.file);

        de.hdodenhof.circleimageview.CircleImageView pic = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.profile);
        TextView name = (TextView)findViewById(R.id.lblName);
        TextView pos = (TextView)findViewById(R.id.lblPos);
        TextView statemnt = (TextView)findViewById(R.id.txtStatment);
        statemnt.setMovementMethod( new ScrollingMovementMethod());

        String personName, position, parse, delim = "~";

        parse = person.name;
        StringTokenizer st = new StringTokenizer(parse, delim);
        personName = st.nextElement().toString();
        position =   st.nextElement().toString().substring(1);

        int identifier = PersonInfo.this.getResources().getIdentifier(person.file, "drawable", PersonInfo.this.getPackageName());
        AssetManager am = getAssets();

        try{
            InputStream is = am.open(fileName);
            BufferedReader input = new BufferedReader( new InputStreamReader(is));

            while( (str = input.readLine()) != null)
                statmentDisplay = statmentDisplay + "\n" + str;
                //Log.d("State",str);
            name.setText(personName);
            pos.setText(position);
            pic.setImageResource(identifier);
            statemnt.setText(statmentDisplay);


        }catch (IOException e){
            e.printStackTrace();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkAvailable()){
                    String[] TO = {person.contact};
                    String[] CC = {""};

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setData(Uri.parse("mailto:"));
                    email.setType("message/rfc822");
                    email.putExtra(Intent.EXTRA_EMAIL, TO);
                    email.putExtra(Intent.EXTRA_CC, CC);
                    email.putExtra(Intent.EXTRA_SUBJECT, "Message Subject:");
                    email.putExtra(Intent.EXTRA_TEXT, "Message Body:");

                    try {
                        startActivity(Intent.createChooser(email, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(PersonInfo.this, "Please ensure their email account set up", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(PersonInfo.this, "Not connected to internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
