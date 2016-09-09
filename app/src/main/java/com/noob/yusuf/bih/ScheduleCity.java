package com.noob.yusuf.bih;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ScheduleCity extends AppCompatActivity {

    Intent in = getIntent();

    TextView txtTo;
    TextView txtFrom;
    TextView lblTo;
    TextView lblFrom;
    TextView lblFromDepart;
    TextView lblToDepart;
    TextView txtFromDepart;
    TextView txtToDepart;
    TextView txtDesclaimer;
    Button btnTunusStop;
    Button btnSihhiyeStop;
    Button btnSchedule;

    int type = 0;
    String toCity, fromCity;

    ProgressDialog progressDialog;
    FileInputStream fs;
    BufferedReader input;
    String str, fileType = "", site = "";
    boolean found = false;

    ArrayList<String> fromCityList = new ArrayList<String>();
    ArrayList<String> toCityList = new ArrayList<String>();

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
            Intent in = new Intent(ScheduleCity.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else
            startActivity( new Intent(ScheduleCity.this, About.class));
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_city);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BIH ~ Bus Schedule");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getIntent().getStringExtra("Color"))));

        site = getIntent().getStringExtra("Site");
        type = getIntent().getIntExtra("Type", 0);

        txtTo = (TextView)findViewById(R.id.txtTimeTo);
        txtFrom = (TextView)findViewById(R.id.txtTimeFrom);
        lblFrom = (TextView)findViewById(R.id.lblTimeFrom);
        lblTo = (TextView)findViewById(R.id.lblTimeTo);
        lblToDepart = (TextView)findViewById(R.id.lblTimeToDepart);
        lblFromDepart = (TextView)findViewById(R.id.lblTimeFromDepart);
        txtToDepart = (TextView)findViewById(R.id.txtTimeToDepart);
        txtFromDepart = (TextView)findViewById(R.id.txtTimeFromDepart);
        txtDesclaimer = (TextView)findViewById(R.id.txtBusInfo);
        txtDesclaimer.setMovementMethod( new ScrollingMovementMethod());
        btnSchedule = (Button)findViewById(R.id.btnSchedule);
        btnTunusStop = (Button)findViewById(R.id.btnTunusStops);
        btnSihhiyeStop = (Button)findViewById(R.id.btnSihhiyeStops);

        new CheckUpdate();

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);

        if( day == 1 || day == 7){
            fileType = "End";
        }
        else
            fileType = "Week";

        Log.d("daySelected", day +"");

        if (type == 0){
            fromCity = "fromMain" + fileType;
            toCity = "toMain" + fileType;
            Log.d("Campus", "main");
        }
        else{
            fromCity = "fromEast" + fileType;
            toCity = "toEast" + fileType;
            Log.d("Campus", "East");
        }


        try{
            Log.d("fileOpened", toCity);
            fs = openFileInput(toCity);
            input = new BufferedReader (new InputStreamReader (fs));

            while( (str = input.readLine()) != null){
                toCityList.add(str);
            }

            str = "";
            fs = openFileInput(fromCity);
            input = new BufferedReader (new InputStreamReader (fs));

            while( (str = input.readLine()) != null){
                fromCityList.add(str);
            }

             DisplayTimes();

        }catch( IOException e){
            e.printStackTrace();
            Log.d("FileResult", "NotFound");
        }

        Button getList = (Button)findViewById(R.id.btnGet);

        getList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Update().execute();
            }
        });

        final Intent in = new Intent(ScheduleCity.this, LifeDialog.class);

        btnSihhiyeStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.putExtra("File","busSihhiyeStop");
                in.putExtra("Title","Sihhiye Service");
                in.putExtra("Link", "http://www.bilkent.edu.tr/bilkent/admin-unit/transport/sm_route_tr_eng/sm_route_tr_eng.html");
                in.putExtra("Complexity", 2);
                in.putExtra("Type",2);
                in.putExtra("Description", "Locations of Bus Stops may vary according to change in Bilkent Transportation regulations. Please check more information for detailed stop plans.");
                startActivity(in);
            }
        });

        btnTunusStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.putExtra("File","busTunusStop");
                in.putExtra("Title","Tunus Service");
                in.putExtra("Link", "http://www.bilkent.edu.tr/bilkent/admin-unit/transport/tm_route_tr_eng/tm_route_tr_eng.html");
                in.putExtra("Complexity", 2);
                in.putExtra("Type",2);
                in.putExtra("Description", "Locations of Bus Stops may vary according to change in Bilkent Transportation regulations. Please check more information for detailed stop plans.");
                startActivity(in);
            }
        });

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()){
                    Intent site = new Intent(Intent.ACTION_VIEW);
                    site.setData(Uri.parse("http://w3.bilkent.edu.tr/bilkent/transportation/"));
                    startActivity(site);
                }
                else{
                    Toast.makeText(ScheduleCity.this, "Not connected to internet",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private class Update extends AsyncTask<Void, Void, Void> {
        String title;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ScheduleCity.this);
            progressDialog.setMessage("Updating Schedule...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            new UpdateBus(site, ScheduleCity.this, type);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
        }
    }

    public   void DisplayTimes(){


            Collections.sort(toCityList);
            Collections.sort(fromCityList);
            SimpleDateFormat timeStyle = new SimpleDateFormat("HH:mm:ss");

            try{
                Calendar c = Calendar.getInstance();

                int hr24=c.get(Calendar.HOUR_OF_DAY);
                int min=c.get(Calendar.MINUTE);

                String time = hr24 + ":" + min + ":00";

                Date systemTime = timeStyle.parse(time);
                Date readTime;

                for (int i = 0 ; (i < fromCityList.size()) && (!found); i++){
                    Log.d("timeList", fromCityList.get(i).substring(0,5));
                    time = fromCityList.get(i).substring(0,5);
                    time = time + ":00";
                    readTime = timeStyle.parse(time);
                    Log.d("readTime", readTime + "");
                    Log.d("systemTime", systemTime + "");
                    if (systemTime.compareTo(readTime)<0){

                        txtFrom.setText(fromCityList.get(i).substring(0,5));
                        txtFromDepart.setText(fromCityList.get(i).substring(6));
                        found = true;
                    }
                }
                if((!found) && (fromCityList.size() > 0)){
                    if(fromCityList.get(0).length() == 0){
                        txtFrom.setText(fromCityList.get(1).substring(0,5));
                        txtFromDepart.setText(fromCityList.get(1).substring(6));
                    }
                    else{
                        txtFrom.setText(fromCityList.get(0).substring(0,5));
                        txtFromDepart.setText(fromCityList.get(0).substring(6));
                    }

                    found = true;
                }

                found = false;

                for (int i = 0 ; (i < toCityList.size()) && (!found); i++) {
                    if ((toCityList.get(i).length() > 5)){

                        time = toCityList.get(i).substring(0, 5);

                        time = time + ":00";
                        readTime = timeStyle.parse(time);

                        if (systemTime.compareTo(readTime) < 0) {
                            txtTo.setText(toCityList.get(i).substring(0,5));
                            txtToDepart.setText(toCityList.get(i).substring(6));
                            found = true;
                        }
                    }
                }

                if((!found) && (toCityList.size() > 0)){
                    if(toCityList.get(0).length() == 0){
                        txtFrom.setText(toCityList.get(1).substring(0,5));
                        txtFromDepart.setText(fromCityList.get(1).substring(6));
                    }
                    else{
                        txtFrom.setText(toCityList.get(0).substring(0,5));
                        txtFromDepart.setText(toCityList.get(0).substring(6));
                    }
                    found = true;
                }

            }catch( ParseException e){
                e.printStackTrace();
                Log.d("operations", "Not Parsing");
            }


    }

    public class CheckUpdate{
        InputStream is;
        FileOutputStream newStamp;
        public CheckUpdate(){
            AssetManager am = getAssets();


            String path = getFilesDir().getAbsolutePath()+"/stamp";
            File stamp = new File(path);
            //stamp.delete();
            if( !stamp.exists()){
                try{
                    Log.d("monthSelected", "Opening assests");
                    is = am.open("stamp");
                    newStamp = openFileOutput("stamp", Context.MODE_PRIVATE);

                    Log.d("monthSelected", "checking");
                    Calendar c = Calendar.getInstance();
                    int systemMonth = c.get(Calendar.MONTH);
                    BufferedReader input = new BufferedReader( new InputStreamReader(is));
                    Log.d("monthSelected", input.readLine() +"");
                    int readMonth =  0;//Integer.parseInt(input.readLine());

                    if(readMonth != systemMonth ){
                        Log.d("monthSelected", "difference in Month");
                        new Update().execute();
                        Log.d("monthSelected", "writting: "+ systemMonth);
                        newStamp.write( (systemMonth+"").getBytes());
                        newStamp.close();
                        DisplayTimes();
                    }
                    else{
                        Log.d("monthSelected", "no difference");
                    }


                }catch ( IOException e){
                    e.printStackTrace();
                }
            }
            else {
                try {

                    Log.d("monthSelected", "Creating file");

                    FileInputStream fs = openFileInput("stamp");
                    input = new BufferedReader( new InputStreamReader(fs));
                    //Log.d("monthSelected", input.readLine() +" ~TEST");

                    Log.d("monthSelected", "checking");
                    Calendar c = Calendar.getInstance();
                    int systemMonth = c.get(Calendar.MONTH);
                    int readMonth = Integer.parseInt(input.readLine());

                    if(readMonth != systemMonth ){
                        newStamp = openFileOutput("stamp", Context.MODE_PRIVATE);
                        Log.d("monthSelected", "difference in Month");
                        new Update().execute();
                        Log.d("monthSelected", "writting: "+ systemMonth);
                        newStamp.write( (systemMonth+"").getBytes());
                        DisplayTimes();
                        newStamp.close();
                    }
                    else{
                        Log.d("monthSelected", "no difference");
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
