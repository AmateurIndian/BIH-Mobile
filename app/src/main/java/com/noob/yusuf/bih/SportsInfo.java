package com.noob.yusuf.bih;

/**
 * Created by yusuf on 09.08.2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class SportsInfo extends Fragment {

    String [] linkNames = {"Sports Personnel", "Sports Facilites", "University Sports Teams", "Sports Courses", "Elective Credit Courses"};
    String [] links = {"http://library.bilkent.edu.tr/","http://library.bilkent.edu.tr/","http://library.bilkent.edu.tr/","http://library.bilkent.edu.tr/","http://library.bilkent.edu.tr/"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragview, container, false);

        TextView txtInfo = (TextView)fragView.findViewById(R.id.txtLifeInfo);
        txtInfo.setMovementMethod( new ScrollingMovementMethod());
        ImageView imgInfo = (ImageView)fragView.findViewById(R.id.imgLifePic);
        ListView lstInfoLins = (ListView)fragView.findViewById(R.id.lstLifeLinks);
        TextView txtFragTitle = (TextView)fragView.findViewById(R.id.txtFragTitle);
        txtFragTitle.setText("Sports");

        imgInfo.setImageResource(R.drawable.sport);

        txtInfo.setText(R.string.sportInfo);
        txtInfo.setMovementMethod( new ScrollingMovementMethod());

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,linkNames);
        lstInfoLins.setAdapter(adapter);

        lstInfoLins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = links[position];
                Intent in = new Intent(getActivity(), LifeDialog.class);
                Log.d("OptionSelected", position+"");
                switch (position){

                   case 0:{
                       in.putExtra("File","sportsPersonel");
                       in.putExtra("Title","Sports Personel");
                       in.putExtra("Link", "http://www.bilkent.edu.tr/~spor/");
                       in.putExtra("Complexity", 1);
                       in.putExtra("Type",1);
                       in.putExtra("Description", "Bilkent Sports personnel are trained individuals in their respective fields and are always available for assistance.");
                       startActivity(in);
                       break;
                   }
                   case 1:{
                       in.putExtra("File","sportsFacilities");
                       in.putExtra("Title","Facilities");
                       in.putExtra("Link", "http://www.bilkent.edu.tr/~spor/");
                       in.putExtra("Complexity", 0);
                       in.putExtra("Type",0);
                       in.putExtra("Description", "To use any sports facility or participate in a sports course, you are required to have your Bilkent University ID card with you everytime you enter the facility.\n" +
                               "\n" +
                               "You can make reservations for the mini football fields, tennis courts and swimming pool by phone or in person at the reception desk in the Physical Education and Sports Center. Your Bilkent University ID card is required for reservations. The keys for indoor tennis courts can be obtained from the Dormitories Sports Hall reception desk. ");
                       startActivity(in);
                       break;
                   }
                    case 2:{
                        in.putExtra("File","sportsTeams");
                        in.putExtra("Title","University Sports Teams");
                        in.putExtra("Link", "http://www.bilkent.edu.tr/~spor/");
                        in.putExtra("Complexity", 0);
                        in.putExtra("Type",0);
                        in.putExtra("Description", "Sports teams have trials for new members at the start of everysemester. Please check more information for names of coaches and trial schedules.");
                        startActivity(in);
                        break;
                    }
                    case 3:{
                        in.putExtra("File","sportsPaid");
                        in.putExtra("Title","Courses");
                        in.putExtra("Link", "http://www.bilkent.edu.tr/~spor/");
                        in.putExtra("Complexity", 0);
                        in.putExtra("Type",0);
                        in.putExtra("Description", "These are charged courses where fees can range from 100tl to 200tl per month, dpending on the course. Please check More Information for detailed description of each course, schedule, and fee structure.");
                        startActivity(in);
                        break;
                    }
                    case 4:{
                        in.putExtra("File","sportsCourses");
                        in.putExtra("Title","Elective Courses");
                        in.putExtra("Link", "http://www.bilkent.edu.tr/~spor/");
                        in.putExtra("Complexity", 0);
                        in.putExtra("Type",0);
                        in.putExtra("Description", "These are courses that can be taken up as additional courses by students in a normal semester. Please check More Information for detailed description of each course, and its credit structure.");
                        startActivity(in);
                        break;
                    }
               }
            }
        });
        return fragView;
    }

}
