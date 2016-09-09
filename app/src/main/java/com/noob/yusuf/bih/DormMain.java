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

public class DormMain extends AppCompatActivity {

    LinearLayout maleSection;
    LinearLayout femaleSection;

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
            Intent in = new Intent(DormMain.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else
            startActivity( new Intent(DormMain.this, About.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dorm_main);
        setTitle("Dormitories");
        android.app.ActionBar ab = getActionBar();

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BIH ~ Dormitories");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.blue))));
        maleSection = (LinearLayout)findViewById(R.id.male);
        maleSection.setOnClickListener(new maleSec());

        femaleSection = (LinearLayout)findViewById(R.id.female);
        femaleSection.setOnClickListener(new femaleSec());

    }

    class maleSec implements View.OnClickListener{

        public void onClick(View v){
            Intent list = new Intent(DormMain.this, RoomList.class);
            list.putExtra("roomList", "maleDorm");
            list.putExtra("color", getResources().getString(R.string.skyBlue));
            startActivity(list);
        }
    }


    class femaleSec implements View.OnClickListener{

        public void onClick(View v){
            Intent list = new Intent(DormMain.this, RoomList.class);
            list.putExtra("roomList", "femaleDorm");
            list.putExtra("color", getResources().getString(R.string.red));
            startActivity(list);
        }
    }
}
