package com.noob.yusuf.bih;

/**
 * Created by yusuf on 19.08.2016.
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

public class RegOther extends Fragment {

    String [] links = {""};
    String [] linkNames =   {"Dormitory Registration", "GE-100 Orientation", "Stars - SRS", "International Office"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragview, container, false);

        TextView txtInfo = (TextView)fragView.findViewById(R.id.txtLifeInfo);
        ImageView imgInfo = (ImageView)fragView.findViewById(R.id.imgLifePic);
        ListView lstInfoLins = (ListView)fragView.findViewById(R.id.lstLifeLinks);
        TextView txtFragTitle = (TextView)fragView.findViewById(R.id.txtFragTitle);
        txtFragTitle.setText("Miscellaneous Information");

        imgInfo.setImageResource(R.drawable.misc);
        txtInfo.setText("For additional information please feel free to contact the International Office, BIH members or any other Bilkent representative. Contact details can be found from the Contact option of this app.");
        txtInfo.setMovementMethod( new ScrollingMovementMethod());
        //final RegRow adapter = new RegRow(getActivity(),linkNames);
        //
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,linkNames);
        lstInfoLins.setAdapter(adapter);

        lstInfoLins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int selected = position;

                Intent in = new Intent(getActivity(), LifeDialog.class);
                switch (selected){
                    case 0 : {
                        in.putExtra("File","EMPTY");
                        in.putExtra("Title","Dormitory Registration");
                        in.putExtra("Link", "http://www.bilkent.edu.tr/bilkent-tr/admin-unit/yurt/e_yurt_ucret.html");
                        in.putExtra("Complexity", 0);
                        in.putExtra("Type",0);
                        in.putExtra("Description", "Bilkent University offers on-campus housing for both national and international students. The dormitories provide a safe, clean, and comfortable atmosphere for more than 4,000 students. \n\nStudents who want to guarantee a dormitory room on campus must confirm by 31 July 2016 " +
                                "their intention to attend Bilkent University by clicking the confirmation link sent to them in the " +
                                "acceptance e-mail. Although the confirmation guarantees a dormitory room on campus, the " +
                                "room type may differ based on availability. Students will be required to pay a confirmation fee of " +
                                "50 USD to complete the confirmation process. This fee will be deducted from the dormitory fee " +
                                "after the student registers to the dormitories. Please note that this fee will NOT be refunded if " +
                                "the student decides not to register to the dormitories.\n\n "+ "Those who confirm their attendance at Bilkent University will be able to register to the\n\n" +
                                "dormitories after completing the registration which will take place 31 August – 2 September " +
                                "2016. Students can apply to the Dormitories Registration Desk located in the registration area " +
                                "by presenting their Bilkent student ID card and dormitory fee for the fall semester. After " +
                                "completing dormitory registration students will be entitled to check into the dormitories on the " +
                                "same day.\n\n"+ "Those who do not register to the dormitories during the registration period will lose their chance\n\n" +
                                "of a guaranteed dormitory room, even if they have confirmed their attendance at Bilkent " +
                                "University before 31 July 2016. On-campus accommodation will not be guaranteed for students " +
                                "who do not confirm their attendance at Bilkent University by 31 July 2016 or for those who " +
                                "apply to the university after 31 July 2016. In such cases the university will make efforts to help " +
                                "find off-campus accommodation for students. ");
                        startActivity(in);
                        break;
                    }
                    case 1 : {
                        in.putExtra("File","EMPTY");
                        in.putExtra("Title","Dormitory Registration");
                        in.putExtra("Link", "http://www.bilkent.edu.tr/bilkent/academic/international/orientation.html");
                        in.putExtra("Complexity", 0);
                        in.putExtra("Type",0);
                        in.putExtra("Description", "The orientation program, designed as a one-credit required course, GE 100 Introduction to " +
                                "Academic Life, will take place 6 - 8 September 2016.\n\n" +
                                "This GE 100 Introduction to Academic Life course is designed to welcome and acclimatize students " +
                                "to the university’s academic and social environment. It is a collection of activities rather than a " +
                                "traditional course and an experience which past participants have enjoyed. \n\n" +
                                "The university offers the course to introduce its programs and facilities to all incoming students." +
                                "The program booklet will be provided at registration. ");
                        startActivity(in);
                        break;
                    }
                    case 2 : {
                        in.putExtra("File","EMPTY");
                        in.putExtra("Title","Dormitory Registration");
                        in.putExtra("Link", "http://stars.bilkent.edu.tr/srs");
                        in.putExtra("Complexity", 0);
                        in.putExtra("Type",0);
                        in.putExtra("Description", "STARS is the name of the Bilkent University academic information service. Students have access " +
                                "to the STARS system through the SRS (Student Review System) module. This service allows you " +
                                "to learn your COPE exam location and results, to register for courses and to access information " +
                                "such as your grades, transcript, curriculum, etc. Your initial SRS password will be provided to " +
                                "you along with your Bilkent ID card during registration.");
                        startActivity(in);
                        break;
                    }
                    case 3 : {
                        in.putExtra("File","internationalPersonel");
                        in.putExtra("Title","International Office");
                        in.putExtra("Link", "http://www.bilkent.edu.tr/phonedir/dep/totym.htm");
                        in.putExtra("Complexity", 2);
                        in.putExtra("Type",4);
                        in.putExtra("Description", "The Office of International Students plays a significant role on the internationalization path of Bilkent University by promoting its programs and providing support for all international academic affairs. " +
                                "\n\n" +
                                "In order to ensure easy transition of the students, the Office provides assistance and guidance to both international degree-seeking students and incoming/outgoing exchange students through all phases of their academic and social conduct. As well as providing information on procedural matters, such as application process, course selection and accommodation opportunities, via orientation programs and cultural trips, the Office of International Students offers ongoing help to all international students." +
                                "\n\n" +
                                "We will always be happy to host you at Bilkent University and hope you all have a memorable time here to remember and share with your family and friends for many more years ahead. ");
                        startActivity(in);
                        break;
                    }
                }

            }
        });

        return fragView;
    }
}
