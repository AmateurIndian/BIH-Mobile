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

public class RegTuition  extends Fragment {

    String [] links = {""};
    String [] linkNames =   {"The tuition fee for the 2016-2017 academic year is 14,500 USD (8% VAT included).",
                             "The tuition fee for the fall semester (7,250 USD) should be paid before registration.",
                             "International tuition fees can be paid in Turkish Lira (TL) only within Turkey. If international " +
                                     "students prefer to pay the tuition amount in Turkish Lira instead of USD, the USD effective " +
                                     "exchange rate of the Central Bank of Turkey on date of payment will be used to calculate the TL " +
                                     "amount.",
                             "Representatives of the banks will be present in the registration area and students can make " +
                                     "tuition payments to them after completing the pre-registration process at the registration desk.",
                             "Students may also wire transfer the tuition fee from their home country at least one week " +
                                     "before the registration date to the bank account provided below. Please note that students " +
                                     "should have their first name and last name written on the bank receipt and that this receipt " +
                                     "needs to be submitted to the registration desk during registration." +
                                     "<br><br>" +
                                     "<br>\tGaranti Bank / Bilkent Branch" +
                                     "<br>\tUSD Account Number: 393-9088737" +
                                     "<br>\tUSD IBAN: TR10 0006 2000 3930 0009 0887 37" +
                                     "<br>\tSWIFT CODE: TGBATRIS"
                             };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragview, container, false);

        TextView txtInfo = (TextView)fragView.findViewById(R.id.txtLifeInfo);
        ImageView imgInfo = (ImageView)fragView.findViewById(R.id.imgLifePic);
        ListView lstInfoLins = (ListView)fragView.findViewById(R.id.lstLifeLinks);
        TextView txtFragTitle = (TextView)fragView.findViewById(R.id.txtFragTitle);
        txtFragTitle.setText("Tuition Fees");

        imgInfo.setImageResource(R.drawable.fee);

        txtInfo.setText(R.string.tuitonInfo);
        txtInfo.setMovementMethod( new ScrollingMovementMethod());

        final RegRow adapter = new RegRow(getActivity(),linkNames);
        lstInfoLins.setAdapter(adapter);


        return fragView;
    }
}
