package com.noob.yusuf.bih;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RoomInfo extends AppCompatActivity {
    String url = "http://www.bilkent.edu.tr/bilkent-tr/admin-unit/yurt/e_yurt.html";
    Button btnInfo;
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
            Intent in = new Intent(RoomInfo.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else
            startActivity( new Intent(RoomInfo.this, About.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);

        Intent recieved = getIntent();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String barColor = recieved.getStringExtra("color");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(barColor)));


        String type = recieved.getStringExtra("type");
        String fees = recieved.getStringExtra("fees");
        String bed = recieved.getStringExtra("bedFurn");
        String furn = recieved.getStringExtra("furn");
        String cap = recieved.getStringExtra("cap");
        String img = "NULL";

        TextView typeDisp = (TextView)findViewById(R.id.typeDisp);
        TextView feesDisp = (TextView)findViewById(R.id.feeDisp);
        TextView capDisp = (TextView)findViewById(R.id.capDisp);
        TextView bedDisp = (TextView)findViewById(R.id.bedDisp);
        bedDisp.setMovementMethod( new ScrollingMovementMethod());
        TextView furnDisp = (TextView)findViewById(R.id.furnDisp);
        furnDisp.setMovementMethod( new ScrollingMovementMethod());
        //ImageView imgDisp = (ImageView)findViewById(R.id.imgDisp);

        typeDisp.setText("Room Type: " + type);
        feesDisp.setText("Semester Fees:\n" + fees);
        capDisp.setText("Max Capacity:\n" + cap);
        bedDisp.setText( bed);
        furnDisp.setText( furn);
        //imgDisp.setImageResource(R.drawable.oda);
        btnInfo = (Button)findViewById(R.id.btnRoomInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()){
                    Intent site = new Intent(Intent.ACTION_VIEW);
                    site.setData(Uri.parse(url));
                    startActivity(site);
                }
                else{
                    Toast.makeText(RoomInfo.this, "Not connected to internet",
                            Toast.LENGTH_LONG).show();
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
