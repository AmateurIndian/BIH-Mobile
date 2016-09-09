package com.noob.yusuf.bih;

import java.util.List;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by yusuf on 29.07.2016.
 */
public class CustomAdapter extends BaseAdapter {
        Activity customActivity;
        int id;
        LayoutInflater inflater;
        List<Person> items;
        int identifier = 0;
        public CustomAdapter(Activity context, List<Person> items) {
            super();
            identifier = id;
            this.items = items;
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
            Person selected = items.get(position);

            int identifier = customActivity.getResources().getIdentifier(selected.file, "drawable", customActivity.getPackageName());

            if(convertView==null)
               row = inflater.inflate(R.layout.row_layout, null);

            de.hdodenhof.circleimageview.CircleImageView im = (de.hdodenhof.circleimageview.CircleImageView) row.findViewById(R.id.imgThumbnail);
            TextView name = (TextView) row.findViewById(R.id.txtName);
            TextView email = (TextView) row.findViewById(R.id.txtContact);

           // Integer id = Integer.parseInt(imageName);
            String parse = selected.name;
            String delim = "~";
            StringTokenizer st = new StringTokenizer(parse, delim);
            String personName = st.nextElement().toString();
            String pos =   st.nextElement().toString().substring(1);

             im.setImageResource(identifier);
             name.setText(personName);
             email.setText(pos);



            return row;
        }

}
