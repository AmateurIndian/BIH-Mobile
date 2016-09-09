package com.noob.yusuf.bih;

import android.content.res.AssetManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MapsUtil extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng selected;
    Site recieved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_util);
        Bundle data = getIntent().getExtras();
        recieved = (Site)data.getParcelable("Site");
        selected = new LatLng(recieved.lat, recieved.lng);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String str, delim = ",";
        AssetManager am = getAssets();
        StringTokenizer st;
        ArrayList<Site> list = new ArrayList<Site>();
        Site currSite;
        mMap.addMarker( new MarkerOptions().position(selected).title(recieved.title)).showInfoWindow();
        Log.d("Move", selected.latitude+"");

        mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(selected));



        try{

            InputStream is = am.open("loc.txt");
            BufferedReader input = new BufferedReader( new InputStreamReader(is));

            while ((str = input.readLine()) != null){
                st = new StringTokenizer(str, delim);
                currSite = new Site(st.nextElement().toString(), st.nextElement().toString(), st.nextElement().toString(), st.nextElement().toString());
                list.add( currSite);
                mMap.addMarker( new MarkerOptions().position( new LatLng(currSite.lat,currSite.lng)).title(currSite.title));
            }



        }catch(IOException e){
            e.printStackTrace();
            Log.d("Map", "File not found");
        }

    }

}
