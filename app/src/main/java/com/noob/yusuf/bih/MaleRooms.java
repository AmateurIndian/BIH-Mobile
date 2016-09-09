package com.noob.yusuf.bih;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MaleRooms extends AppCompatActivity {
    ArrayList<String> roomList = new ArrayList<String>();
    TextView dispTest;
    BufferedReader input;
    ListView roomDisp;
    int count = 0;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_rooms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //dispTest = (TextView)findViewById(R.id.textDisp);
        AssetManager am = getAssets();
        roomDisp = (ListView)findViewById(R.id.roomView);

        try{
            count = 0;
            InputStream is = am.open("femaleDorm");
            input = new BufferedReader( new InputStreamReader(is));

            while((str = input.readLine()) != null){
                //dispList.add((TextView)((TextView) findViewById(R.id.textDisp)));
                //dispList.get(count).append(str +"\n");
                roomList.add(str);
                count++;
            }
            for(int i = 0; i < count; i++){
                Log.d("Out", roomList.get(i));
            }
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,roomList);

            roomDisp.setAdapter(adapter);



        }catch(IOException ex){
            ex.printStackTrace();
        }


    }
}
