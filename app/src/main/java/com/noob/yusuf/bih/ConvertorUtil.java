package com.noob.yusuf.bih;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConvertorUtil extends AppCompatActivity {
    TextView lblFrom, lblTo;
    EditText txtFrom, txtTo;
    Button btnSwap, btnConvert;
    Spinner spnFrom, spnTo;
    String ans = "";
    double resultantAmt;

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
            Intent in = new Intent(ConvertorUtil.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }
        else
            startActivity( new Intent(ConvertorUtil.this, About.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor_util);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.favicon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BIH ~ Curency Convertor");

        Intent in = getIntent();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(in.getStringExtra("color"))));

        lblFrom = (TextView)findViewById(R.id.lblFrom);
        lblTo = (TextView)findViewById(R.id.lblTo);
        txtFrom = (EditText) findViewById(R.id.txtFrom);
        txtTo = (EditText) findViewById(R.id.txtTo);
        btnSwap = (Button)findViewById(R.id.btnSwap);
        btnConvert = (Button)findViewById(R.id.btnConvert);
        spnFrom = (Spinner)findViewById(R.id.spnFrom);
        spnTo = (Spinner)findViewById(R.id.spnTo);
        ArrayList<String> currencyList = new ArrayList<String>();
        String str;
        AssetManager am = getAssets();

        try{

            InputStream is = am.open("currency");
            BufferedReader input = new BufferedReader( new InputStreamReader( is));


            while( (str = input.readLine()) != null){
                currencyList.add(str);
            }

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,currencyList);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spnFrom.setAdapter(adapter);
            spnFrom.setSelection(146);
            spnTo.setAdapter(adapter);
            txtTo.setEnabled(false);

            String pair = getPair();
            double amt = Double.parseDouble(txtFrom.getText().toString());
            convert(pair, amt);

        }catch(IOException e){
            e.printStackTrace();
            Log.d("FileResult", "File not found");
        }

        btnSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = spnFrom.getSelectedItemPosition();
                double amt;
                spnFrom.setSelection( spnTo.getSelectedItemPosition());
                spnTo.setSelection(temp);
                String pair = getPair();
                if(txtFrom.getText().toString().equals(""))
                    txtFrom.setText("1.0");
                amt = Double.parseDouble(txtFrom.getText().toString());
                convert(pair, amt);
            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            double amt;
            @Override
            public void onClick(View v) {
                String pair = getPair();
                amt = Double.parseDouble(txtFrom.getText().toString());
                convert(pair, amt);
            }
        });

    }

    public String getPair(){
        String pair = "";
        pair = spnFrom.getSelectedItem().toString().substring(0,3) + spnTo.getSelectedItem().toString().substring(0,3);
        Log.d("Pair",pair);
        return pair;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void convert( String fromTo, final double amt){
        if (!isNetworkAvailable()){
            Toast.makeText(ConvertorUtil.this, "Not connected to internet",
                    Toast.LENGTH_LONG).show();
        }
        else{
            class executeRequest extends AsyncTask<String, Void, String> {
                ProgressDialog loading;
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = new ProgressDialog(ConvertorUtil.this);
                    loading.setMessage("Getting Rate...");
                    loading.setIndeterminate(false);
                    loading.show();
                }
                @Override
                protected String doInBackground(String... urls) {

                    return GET(urls[0]);
                }
                // onPostExecute displays the results of the AsyncTask.
                @Override
                protected void onPostExecute(String result) {
                    parse(result);
                    Log.d("Parse", result);
                    if (ans != ""){
                        resultantAmt = calculate( Double.parseDouble(ans), amt);
                    }
                    else
                        resultantAmt = 0;

                    txtTo.setText( resultantAmt +"");
                    loading.dismiss();
                }
            }
            new executeRequest().execute("http://query.yahooapis.com/v1/public/yql?q=%20select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22" + fromTo + "%22)%20&env=store://datatables.org/alltableswithkeys&format=json");
        }
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
    public void parse( String str){
        Log.d("ParseStr", str);
        JSONObject obj;
        try{
            obj = new JSONObject(str);
            obj= obj.getJSONObject("query");
            obj = obj.getJSONObject("results");
            obj = obj.getJSONObject("rate");
            ans = obj.optString("Rate").toString();
            Log.d("Parse", ans);

        }catch( JSONException e){
            e.printStackTrace();
            Log.d("Parse","Failed");
        }
    }
    public double calculate( double rate, double amt){
        return rate*amt;
    }
}
