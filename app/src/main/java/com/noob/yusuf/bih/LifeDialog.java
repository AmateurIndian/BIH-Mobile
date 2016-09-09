package com.noob.yusuf.bih;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LifeDialog extends Activity {

    int complex;
    String file, addInfo;
    String str;
    ArrayList<String> content = new ArrayList<String>();
    ArrayList<String> link = new ArrayList<String>();
    ArrayList<String> coordinates = new ArrayList<String>();
    int type;
    ListView list;
    Button  btnAddInfo;
    TextView  txtTitle, txtDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_dialog);


        Intent in = getIntent();
        file = in.getStringExtra("File");
        addInfo  = in.getStringExtra("Link");
        complex = in.getIntExtra("Complexity", 0);
        type = in.getIntExtra("Type", -1);


        list = (ListView)findViewById(R.id.lstinfoDisp);

        btnAddInfo = (Button)findViewById(R.id.btnMoreInfo);

        txtTitle = (TextView)findViewById(R.id.txtInfoTitle);
        str = in.getStringExtra("Title");
        txtTitle.setText(str);

        txtDesc = (TextView)findViewById(R.id.txtDesc);
        str = in.getStringExtra("Description");
        txtDesc.setMovementMethod( new ScrollingMovementMethod());
        txtDesc.setText(str);


        if (file.equals("EMPTY")){
            list.setVisibility(View.GONE);
            list.setEnabled(false);
            txtTitle.setVisibility(View.GONE);
            txtTitle.setEnabled(false);
            txtDesc.setMaxLines(10000);
            txtDesc.setGravity(Gravity.LEFT);
            Log.d("TestingFile", "Hello");
        }



        AssetManager am = getAssets();
        try{
            InputStream is = am.open(file);
            BufferedReader input = new BufferedReader( new InputStreamReader(is));

            while ( (str = input.readLine()) != null){
                content.add(str);
                if(complex == 1)
                    link.add( (str = input.readLine()));
                else if(complex == 2){
                    link.add( (str = input.readLine()));
                    coordinates.add((str = input.readLine()));
                }

            }




        }catch (IOException e){
            e.printStackTrace();
        }

        setList();

        btnAddInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()){
                    Intent site = new Intent(Intent.ACTION_VIEW);
                    site.setData(Uri.parse(addInfo));
                    startActivity(site);
                }
                else{
                    Toast.makeText(LifeDialog.this, "Not connected to internet",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void setList(){

        if (type == 0){
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,content);
            list.setAdapter(adapter);
            Log.d("Testing", file);

        }
        else if( type > 0){
            CustomDialogAdapter adapter = new CustomDialogAdapter(this, content, link, type);
            list.setAdapter(adapter);
            if (type == 1){
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(isNetworkAvailable()){
                            String[] TO = {link.get(position)};
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
                                Toast.makeText(LifeDialog.this, "Please ensure email account set up", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(LifeDialog.this, "Not connected to internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else if (type == 2){
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String loc = coordinates.get(position), str = ",";
                        StringTokenizer st = new StringTokenizer(loc, str);
                        Site site = new Site(st.nextElement().toString(),st.nextElement().toString(), content.get(position), "HealthCenter");

                        Intent map = new Intent(LifeDialog.this, MapsUtil.class);
                        map.putExtra("Site", site);
                        startActivity(map);
                    }
                });
            }
            else if (type == 4){
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(isNetworkAvailable()){
                            String[] TO = {coordinates.get(position)};
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
                                Toast.makeText(LifeDialog.this, "Please ensure email account set up", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(LifeDialog.this, "Not connected to internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
