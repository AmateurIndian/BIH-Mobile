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
import android.widget.ListView;
import android.widget.TextView;

public class RegScholarship extends Fragment {

    String [] links = {""};
    String [] linkNames =   {"Prospective students who apply for financial assistance are evaluated according to their high school grades, their national and/or international exam scores, and other relevant academic criteria.",
                             "Qualified applicants are offered partial or full tuition waiver scholarships. Tuition waiver scholarships are awarded at five levels, ranging from 20% to 100% in increments of 20%. Accommodation scholarships are also available for a limited number of top students who are on full tuition waiver scholarship.",
                             "Students can benefit from tuition waiver scholarships for maximum of 2 years in the English Preparatory Program and for a maximum of 5 years in 4-year undergraduate programs. Accomodation scholarships are provided for 4 years.",
                             "Students on scholarship are required to take at least the minimum course load* from their curriculum specified for their major department each semester (excluding any minor program courses, withdrawn courses**, GE100, GE251 and non-credit courses) and to maintain a minimum annual GPA of 2.00/4.00. (The annual GPA is calculated as a weighted average of Fall and Spring semester grades.) English Preparatory Program students are required to qualify to take the ECA or COPE exam at the end of each course or pass COPE by the end of the academic year. Scholarships of students who fail to meet these conditions will be decreased one or two levels and cannot be restored even if the students improve their performance. If there are no Fall or Spring semester courses within the annual GPA, students with less than 2.00 are given a warning; if these students fail to meet the required conditions at the end of the following year, their scholarships will be decreased by two levels.",
                             "Students on accommodation scholarship are required to maintain a minimum annual GPA of 2. 50/4.00. Students who fail to meet this condition will loose their accommodation scholarship. If their annual GPA is less than 2.00, their scholarship will be decreased by two levels.",
                             "If a scholarship student is subject to a disciplinary action, his/her current scholarship may be reduced or cancelled depending on the disciplinary offense and the sanction imposed.",
                             "Students participated in exchange programs for one or two semesters are required to take and pass a number of courses equal to their minimum course load.",
                             "<b>Merit Scholarship </b>" +
                                     "<br>" +
                                     "At the end of each academic year, international students who have taken at least a minimum curriculum course load (excluding any additional courses, withdrawn courses, GE 100-250-251, non-credit courses and repeated courses in Spring, already taken in Fall semester) and attained an annual grade point average of 3.30 or above, as well as being in the top 5% of all the non-scholarship students in their program, may become eligible to receive a merit scholarship for the following academic year on top of any partial tuition waiver scholarship they may already have. Depending upon a student's ranking within the top 5% the scholarship covers tuition fees to the extent shown below:" +
                                     "<br><br>" +
                                     "<b>\t Rank\t\t\tPercentage Tuition</b>" +
                                     "<br>\t<font color = grey> First 1% \t\t\t100% </font>" +
                                     "<br>\t<font color = grey> Second 1% \t80% </font>" +
                                     "<br>\t<font color = grey> Third 1% \t\t60% </font>" +
                                     "<br>\t<font color = grey> Forth 1% \t\t40% </font>" +
                                     "<br>\t<font color = grey> Fifth 1% \t\t\t20% </font>" +
                                     "<br><br>" + "To become eligible for a merit scholarship, an international student must rank at a level higher than their current partial tuition waiver scholarship level."};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragview, container, false);

        TextView txtInfo = (TextView)fragView.findViewById(R.id.txtLifeInfo);
        ImageView imgInfo = (ImageView)fragView.findViewById(R.id.imgLifePic);
        ListView lstInfoLins = (ListView)fragView.findViewById(R.id.lstLifeLinks);
        TextView txtFragTitle = (TextView)fragView.findViewById(R.id.txtFragTitle);
        txtFragTitle.setText("Scholarship Policies");

        imgInfo.setImageResource(R.drawable.scholar);

        txtInfo.setText(R.string.scholarInfo);
        txtInfo.setMovementMethod( new ScrollingMovementMethod());

        final RegRow adapter = new RegRow(getActivity(),linkNames);
        lstInfoLins.setAdapter(adapter);


        return fragView;
    }
}
