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

public class BookInfo extends Fragment {

    String [] linkNames = {"On Campus Stationary Stores"};
    String [] links = {"http://library.bilkent.edu.tr/"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragview, container, false);

        TextView txtInfo = (TextView)fragView.findViewById(R.id.txtLifeInfo);
        ImageView imgInfo = (ImageView)fragView.findViewById(R.id.imgLifePic);
        ListView lstInfoLins = (ListView)fragView.findViewById(R.id.lstLifeLinks);
        TextView txtFragTitle = (TextView)fragView.findViewById(R.id.txtFragTitle);
        txtFragTitle.setText("Stationary & Books");
        imgInfo.setImageResource(R.drawable.book);


        txtInfo.setText(R.string.bookInfo);
        txtInfo.setMovementMethod( new ScrollingMovementMethod());

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,linkNames);
        lstInfoLins.setAdapter(adapter);

        lstInfoLins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getActivity(), LifeDialog.class);
                switch (position) {

                    case 0: {
                        in.putExtra("File", "bookInfo");
                        in.putExtra("Title", "Campus Stores");
                        in.putExtra("Link", "http://w3.bilkent.edu.tr/bilkent/campus-bookstore/");
                        in.putExtra("Complexity", 2);
                        in.putExtra("Type", 2);
                        in.putExtra("Image", "book");
                        in.putExtra("Description", "Prices of products vary from store to store, and may be different compared to stationary shops in the city.");
                        startActivity(in);
                        break;
                    }
                }


            }
        });
        return fragView;
    }
}
