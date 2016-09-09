package com.noob.yusuf.bih;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView img = (ImageView)findViewById(R.id.imgAbout);
        TextView txt = (TextView)findViewById(R.id.txtAbout);
        txt.setMovementMethod( new ScrollingMovementMethod());
        img.setImageResource(R.drawable.main);
    }
}
