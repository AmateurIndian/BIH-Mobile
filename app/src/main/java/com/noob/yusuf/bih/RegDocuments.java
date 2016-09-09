package com.noob.yusuf.bih;

/**
 * Created by yusuf on 17.08.2016.
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

public class RegDocuments extends Fragment {

    String [] links = {""};
    String [] linkNames =   {"Photocopies of the passport pages including, <br>\ta) identification information, <br>\tb) stamp of the latest entry date to Turkey, and for students from countries which are not exempt from the visa requirement to enter Turkey. <br>\tc) visa issued by the Turkish Consulate in home country. Students who live in Turkey must submit a photocopy of their residence permit. Students who have a diplomatic ID or a “Blue Card” must submit the photocopy of these documents.",
                             "Five recent passport-size photographs.",
                             "Original high school diploma and transcript.",
                             "The “Equivalency Certificate” of high school diploma. <br><br>This certificate verifies that your high school diploma is equivalent to a Turkish high school diploma. Before leaving for Turkey, you can obtain this certificate from the Turkish Consulate in your home country OR after you arrive, from the Ministry of National Education of the Republic of Turkey at the following address: <br>Ankara İl Milli Eğitim Müdürlüğü <br>İ Blok Kültür Bölümü Beşevler, Ankara <br>Phone: +90 312 212 4642 <br><br> To apply for the equivalency certificate, students must submit their original high school diploma and a transcript listing all of the courses taken in high school including the grades and credit hours for each, accompanied by a translation in English or Turkish. ",
                             "Original exam score sheets (SAT, GCE Advanced Level, TQDK, etc.) are required if uploadedduring application. SAT scores must be sent to Bilkent University directly from the College Board<br>" +
                                     "(Code - 9877). <br> Bilkent University reserves the right to verify the validity of exam scores",
                             "English  Language Proficiency exam score sheets (IELTS/TOEFL) must be sent to Bilkent University directly from the related institutions. ",
                             "Tuition fee payment receipt. Students must pay their fall semester tuition fee before the registration.<br>" +
                                     "(Please see “Tuition Fee” section for detailed information.)"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragview, container, false);

        TextView txtInfo = (TextView)fragView.findViewById(R.id.txtLifeInfo);
        ImageView imgInfo = (ImageView)fragView.findViewById(R.id.imgLifePic);
        ListView lstInfoLins = (ListView)fragView.findViewById(R.id.lstLifeLinks);
        TextView txtFragTitle = (TextView)fragView.findViewById(R.id.txtFragTitle);
        txtFragTitle.setText("Documents for Registration");

        imgInfo.setImageResource(R.drawable.docs);

        txtInfo.setText(R.string.docInfo);
        txtInfo.setMovementMethod( new ScrollingMovementMethod());

        final RegRow adapter = new RegRow(getActivity(),linkNames);
        lstInfoLins.setAdapter(adapter);


        return fragView;
    }

}

