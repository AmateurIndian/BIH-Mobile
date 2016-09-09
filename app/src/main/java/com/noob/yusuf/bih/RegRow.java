package com.noob.yusuf.bih;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yusuf on 17.08.2016.
 */
public class RegRow extends BaseAdapter {
    Activity customActivity;
    int id;
    static int count = 1 ;
    LayoutInflater inflater;
    String [] items;
    List<String> extras;
    int identifier = 0;
    public RegRow(Activity context, String [] items) {
        super();

        this.items = items;
        this.extras = extras;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        customActivity = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View row =convertView;
        String selected = items[position];


        //int identifier = customActivity.getResources().getIdentifier(selected.file, "drawable", customActivity.getPackageName());

        if(convertView==null)
            row = inflater.inflate(R.layout.reg_row, null);

        TextView content = (TextView) row.findViewById(R.id.txtLine);
        TextView number = (TextView)row.findViewById(R.id.txtLineNumber);

        content.setText(Html.fromHtml(selected));
        number.setText((position+1) +"");

        return row;
    }
}
