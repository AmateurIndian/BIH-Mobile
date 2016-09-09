package com.noob.yusuf.bih;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class TranslatorUtil extends AppCompatActivity {
    TextView output, lblOut, lblIn;
    EditText input;
    Button toEnglish, toTurkish;

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
            Intent in = new Intent(TranslatorUtil.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else
            startActivity( new Intent(TranslatorUtil.this, About.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator_util);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BIH ~ Turkish Translator");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getIntent().getStringExtra("color"))));


        output = (TextView)findViewById(R.id.output);
        output.setMovementMethod( new ScrollingMovementMethod());
        input = (EditText)findViewById(R.id.input);
        input.setMovementMethod( new ScrollingMovementMethod());
        lblOut = (TextView)findViewById(R.id.outputLabel);
        lblIn = (TextView)findViewById(R.id.inputLabel);
       // input = (EditText)findViewById(R.id.input);

        toEnglish = (Button)findViewById(R.id.turkToEng);
        toEnglish.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(isNetworkAvailable()){
                    final String text = ((EditText) findViewById(R.id.input)).getText().toString();

                    class bgTranslate extends AsyncTask<Void, Void, Void>{
                        String translatedText = "";
                        ProgressDialog loading;
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loading = new ProgressDialog(TranslatorUtil.this);
                            loading.setMessage("Translating...");
                            loading.setIndeterminate(false);
                            loading.show();
                        }

                        @Override
                        protected Void doInBackground(Void... params) {
                            try {

                                translatedText = toEng(text);
                            } catch (Exception e) {
                                e.printStackTrace();
                                translatedText = e.toString();
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result) {

                            ((TextView) findViewById(R.id.output)).setText(translatedText);
                            super.onPostExecute(result);
                            loading.dismiss();
                        }

                    }

                    new bgTranslate().execute();
                }
                else{
                    Toast.makeText(TranslatorUtil.this, "Not connected to internet",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



        toTurkish = (Button)findViewById(R.id.engToTurk);
        toTurkish.setOnClickListener( new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(isNetworkAvailable()){
                    final String text = ((EditText) findViewById(R.id.input)).getText().toString();

                    class bgStuff extends AsyncTask<Void, Void, Void>{

                        String translatedText = "";
                        ProgressDialog loading;
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loading = new ProgressDialog(TranslatorUtil.this);
                            loading.setMessage("Translating...");
                            loading.setIndeterminate(false);
                            loading.show();
                        }
                        @Override
                        protected Void doInBackground(Void... params) {
                            try {

                                translatedText = toTurk(text);
                            } catch (Exception e) {
                                e.printStackTrace();
                                translatedText = e.toString();
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result) {
                            // TODO Auto-generated method stub
                            ((TextView) findViewById(R.id.output)).setText(translatedText);
                            super.onPostExecute(result);
                            loading.dismiss();
                        }

                    }

                    new bgStuff().execute();
                }
                else{
                    Toast.makeText(TranslatorUtil.this, "Not connected to internet",
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

    public String toTurk(String text) throws Exception{
        Translate.setClientId("BilkentInternationalDesiNoob");
        Translate.setClientSecret("HayaSarjeel-99YusufDesiNoob");

        String translatedText = "";
        translatedText = Translate.execute(text,Language.ENGLISH,Language.TURKISH);
        return translatedText;
    }

    public String toEng(String text) throws Exception{
        Translate.setClientId("BilkentInternationalDesiNoob");
        Translate.setClientSecret("HayaSarjeel-99YusufDesiNoob");

        String translatedText = "";

        translatedText = Translate.execute(text,Language.TURKISH,Language.ENGLISH);
        return translatedText;
    }

}
