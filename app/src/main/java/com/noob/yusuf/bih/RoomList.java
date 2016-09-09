package com.noob.yusuf.bih;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RoomList extends AppCompatActivity {

    ArrayList<String> roomList = new ArrayList<String>();
    ArrayList<Room> roomObjects = new ArrayList<Room>();
    TextView dispTest;
    BufferedReader input;
    ListView roomDisp;
    int count = 0;
    String str;

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
            Intent in = new Intent(RoomList.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else
            startActivity( new Intent(RoomList.this, About.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        AssetManager am = getAssets();
        roomDisp = (ListView)findViewById(R.id.roomView);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BIH ~ Dormitories");
        Intent intent = getIntent();
        String fileName = intent.getStringExtra("roomList");
        final String barColor = intent.getStringExtra("color");
        Log.d("colorPassed", barColor);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(barColor)));

        try{
            count = 0;
            InputStream is = am.open(fileName);
            input = new BufferedReader( new InputStreamReader(is));

            while((str = input.readLine()) != null){
                //dispList.add((TextView)((TextView) findViewById(R.id.textDisp)));
                //dispList.get(count).append(str +"\n");
                roomList.add(str);
                roomObjects.add( new Room(str, (str = input.readLine()),(str = input.readLine()),(str = input.readLine()),(str = input.readLine())/*(str = input.readLine())*/));
                count++;
            }


            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,roomList);

            roomDisp.setAdapter(adapter);


            class select implements AdapterView.OnItemClickListener {
                public void onItemClick(AdapterView<?>adapter,View v, int position, long id){

                    Room selRoom = roomObjects.get(position);
                    Intent in = new Intent(RoomList.this, RoomInfo.class);

                    in.putExtra("type", selRoom.type);
                    in.putExtra("fees", selRoom.fees);
                    in.putExtra("bedFurn", selRoom.bedrFurn);
                    in.putExtra("furn", selRoom.roomFurn);
                    in.putExtra("cap", selRoom.dormCap);
                    in.putExtra("color", barColor);
                    //in.putExtra("img", selRoom.imgSrc);

                    startActivity(in);


                }
            }
            roomDisp.setOnItemClickListener( new select());






        }catch(IOException ex){
            ex.printStackTrace();
        }

    }
}
