package com.noob.yusuf.bih;

/**
 * Created by yusuf on 19.08.2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RegPermit extends Fragment {

    String [] links = {};
    String [] linkNames =   {};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragview, container, false);

        TextView txtInfo = (TextView)fragView.findViewById(R.id.txtLifeInfo);
        ImageView imgInfo = (ImageView)fragView.findViewById(R.id.imgLifePic);
        //ListView lstInfoLins = (ListView)fragView.findViewById(R.id.lstLifeLinks);
        TextView txtFragTitle = (TextView)fragView.findViewById(R.id.txtFragTitle);
        txtFragTitle.setText("Residence Permit");

        imgInfo.setImageResource(R.drawable.residence);

        txtInfo.setText(R.string.permitInfo);
        txtInfo.setMovementMethod( new ScrollingMovementMethod());
        txtInfo.setMovementMethod( new ScrollingMovementMethod());
        txtInfo.setMaxLines(100);


        final RegRow adapter = new RegRow(getActivity(),linkNames);
        //lstInfoLins.setAdapter(adapter);
        //lstInfoLins.setEnabled(false);



        return fragView;
    }
}
