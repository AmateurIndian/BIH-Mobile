package com.noob.yusuf.bih;

/**
 * Created by yusuf on 10.08.2016.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RadioLife extends Fragment {

    String [] linkNames = {"Radio Bilkent Main Page"};
    String [] links = {"http://radyobilkent.com/",""};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragview, container, false);

        TextView txtInfo = (TextView)fragView.findViewById(R.id.txtLifeInfo);
        ImageView imgInfo = (ImageView)fragView.findViewById(R.id.imgLifePic);
        ListView lstInfoLins = (ListView)fragView.findViewById(R.id.lstLifeLinks);
        TextView txtFragTitle = (TextView)fragView.findViewById(R.id.txtFragTitle);
        txtFragTitle.setText("Bilkent Radio");

        imgInfo.setImageResource(R.drawable.radio);

        txtInfo.setText(R.string.radioInfo);
        txtInfo.setMovementMethod( new ScrollingMovementMethod());

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,linkNames);
        lstInfoLins.setAdapter(adapter);

        lstInfoLins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = links[position];

                if(((CampusLife)getActivity()).isNetworkAvailable()){
                    Intent site = new Intent(Intent.ACTION_VIEW);
                    site.setData(Uri.parse(url));
                    startActivity(site);
                }
                else
                    Toast.makeText(((CampusLife)getActivity()), "Not connected to internet", Toast.LENGTH_SHORT).show();


            }
        });
        return fragView;
    }
}
