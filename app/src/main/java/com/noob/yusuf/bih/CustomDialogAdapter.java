package com.noob.yusuf.bih;

/**
 * Created by yusuf on 09.08.2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomDialogAdapter extends BaseAdapter {
    Activity customActivity;
    int id;
    LayoutInflater inflater;
    List<String> items;
    List<String> extras;
    int identifier = 0;
    public CustomDialogAdapter(Activity context, List<String> items, List<String> extras, int type) {
        super();
        id= type;
        this.items = items;
        this.extras = extras;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        customActivity = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
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
        String selected = items.get(position);
        String extraSelected = extras.get(position);

        //int identifier = customActivity.getResources().getIdentifier(selected.file, "drawable", customActivity.getPackageName());

        if(convertView==null)
            row = inflater.inflate(R.layout.dialog_row_layout, null);

        ImageView img = (ImageView) row.findViewById(R.id.imgDialogAction);
        TextView content = (TextView) row.findViewById(R.id.txtDialogContent);
        TextView extra = (TextView) row.findViewById(R.id.txtDialogExtra);

        // Integer id = Integer.parseInt(imageName);
        Resources res = Resources.getSystem();
        if ((id == 1)||(id == 4))
            img.setImageDrawable( res.getDrawable(android.R.drawable.ic_dialog_email));
        else if (id == 2)
            img.setImageDrawable( res.getDrawable(android.R.drawable.ic_dialog_map));
        else
            img.setVisibility(View.GONE);

        content.setText(selected);
        extra.setText(extraSelected);

        return row;
    }
}
