package com.noob.yusuf.bih;

/**
 * Created by yusuf on 10.08.2016.
 */

import android.content.Intent;
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

public class PostalInfo extends Fragment {

    String [] linkNames = {"Post Offices", "Banking", "ATM"};
    String [] links = {"http://library.bilkent.edu.tr/"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragview, container, false);

        TextView txtInfo = (TextView)fragView.findViewById(R.id.txtLifeInfo);
        ImageView imgInfo = (ImageView)fragView.findViewById(R.id.imgLifePic);
        ListView lstInfoLins = (ListView)fragView.findViewById(R.id.lstLifeLinks);
        TextView txtFragTitle = (TextView)fragView.findViewById(R.id.txtFragTitle);
        txtFragTitle.setText("Post & Banking");

        imgInfo.setImageResource(R.drawable.post);

        txtInfo.setText(R.string.postInfo);
        txtInfo.setMovementMethod( new ScrollingMovementMethod());

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,linkNames);
        lstInfoLins.setAdapter(adapter);

        lstInfoLins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getActivity(), LifeDialog.class);
                switch (position){

                    case 0:{
                        in.putExtra("File","postalPost");
                        in.putExtra("Title","Post Offices");
                        in.putExtra("Link", "http://w3.bilkent.edu.tr/bilkent/postal-and-banking-services/");
                        in.putExtra("Complexity", 2);
                        in.putExtra("Type",2);
                        in.putExtra("Description", "The campus facilitates a PTT branch near the student office. Other postal services can be found at Ankuva which is at the entrance of Bilkent University");
                        startActivity(in);
                        break;
                    }
                    case 1:{
                        in.putExtra("File","postalBank");
                        in.putExtra("Title","Banks");
                        in.putExtra("Link", "http://w3.bilkent.edu.tr/bilkent/postal-and-banking-services/");
                        in.putExtra("Complexity", 2);
                        in.putExtra("Type",2);
                        in.putExtra("Description", "The campus facilitates a Yapi Kredi bank branch on the ground floor of the EA building. Other banking services can be found at Ankuva which is at the entrance of Bilkent University");
                        startActivity(in);
                        break;
                    }
                    case 2:{
                        in.putExtra("File","postalATM");
                        in.putExtra("Title","Campus ATM");
                        in.putExtra("Link", "http://w3.bilkent.edu.tr/bilkent/postal-and-banking-services/");
                        in.putExtra("Complexity", 2);
                        in.putExtra("Type",2);
                        in.putExtra("Description", "The campus facilitates several ATMs. Other banking services can be found at Ankuva which is at the entrance of Bilkent University");
                        startActivity(in);
                        break;
                    }

                }


            }
        });
        return fragView;
    }
}
