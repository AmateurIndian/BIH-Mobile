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


public class HealthInfo extends Fragment {

    String [] linkNames = {"Services Provided", "Getting Admitted", "Doctors and Office Hours", "On Campus Pharmacies"};
    String [] links = {};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragview, container, false);

        TextView txtInfo = (TextView)fragView.findViewById(R.id.txtLifeInfo);
        ImageView imgInfo = (ImageView)fragView.findViewById(R.id.imgLifePic);
        ListView lstInfoLins = (ListView)fragView.findViewById(R.id.lstLifeLinks);
        TextView txtFragTitle = (TextView)fragView.findViewById(R.id.txtFragTitle);
        txtFragTitle.setText("Health Center");

        imgInfo.setImageResource(R.drawable.health);

        txtInfo.setText(R.string.healthInfo);
        txtInfo.setMovementMethod( new ScrollingMovementMethod());

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,linkNames);
        lstInfoLins.setAdapter(adapter);

        lstInfoLins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getActivity(), LifeDialog.class);
                switch (position){

                    case 0:{
                        in.putExtra("File","healthServices");
                        in.putExtra("Title","Health Services");
                        in.putExtra("Link", "http://www.bilkent.edu.tr/bilkent/services/health.html");
                        in.putExtra("Complexity", 0);
                        in.putExtra("Type",0);
                        in.putExtra("Description", "Every student registering at Bilkent becomes a member of the Health Mutual Aid Fund which provides financial support to the Health Center. The Fund pays for routine medical services for students, such as check-up, consultations, medical tests, medicine, emergency hospital costs, etc. However, costs incurred by long-term illness such as tuberculosis, chronic kidney diseases, autoimmune diseases, chronic congestive heart failure, rheumatoid arthritis, rheumatic heart disease, diabetes mellitus, diabetes insipidus, chronic neurologic diseases, glaucoma, cataract, chronic diseases of the thyroid, chronic diseases of the parathyroid, chronic intestinal diseases, or chronic liver disease are not covered by the Fund.");
                        startActivity(in);
                        break;
                    }
                    case 1:{
                        in.putExtra("File","healthApplying");
                        in.putExtra("Title","Getting Admitted");
                        in.putExtra("Link", "http://www.bilkent.edu.tr/bilkent/services/health.html");
                        in.putExtra("Complexity", 0);
                        in.putExtra("Type",0);
                        in.putExtra("Description", "FOR EMERGENCY ADMISSIONS CALL 0(312) 266 40 50/51, ext. 6666");
                        startActivity(in);
                        break;
                    }

                    case 2:{
                        in.putExtra("File","healthDoctors");
                        in.putExtra("Title","Doctors & Hours");
                        in.putExtra("Link", "http://www.bilkent.edu.tr/bilkent/services/health.html");
                        in.putExtra("Complexity", 1);
                        in.putExtra("Type",3);
                        in.putExtra("Description", "Timings and sitting doctors may change throughout the year");
                        startActivity(in);
                        break;
                    }
                    case 3:{
                        in.putExtra("File","healthPharmacy");
                        in.putExtra("Title","Pharmacies");
                        in.putExtra("Link", "http://www.bilkent.edu.tr/bilkent/services/health.html");
                        in.putExtra("Complexity", 2);
                        in.putExtra("Type",2);
                        in.putExtra("Description", "FOR EMERGENCY CALL 0(312) 266 40 50/51, ext. 6666");
                        startActivity(in);
                        break;
                    }
                }
            }
        });
        return fragView;
    }
}
