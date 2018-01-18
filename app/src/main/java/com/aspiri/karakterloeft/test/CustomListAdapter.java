package com.aspiri.karakterloeft.test;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aspiri.karakterloeft.R;

/**
 * Created by Justin on 10/01/2018.
 */


    public class CustomListAdapter extends ArrayAdapter<String>{
        private final Activity context;
        private final String[] title;
//        private final Integer[] imageId;
        public CustomListAdapter(Activity context,
                                 String[] title) {                //Integer[] imageId imageview could be added to list
            super(context, R.layout.drawer_list_item, title);
            this.context = context;
            this.title = title;
//            this.imageId = imageId;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.drawer_list_item, null, true);
            TextView txtTitle = rowView.findViewById(R.id.lblListItem);
//            ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
            txtTitle.setText(title[position]);
//            imageView.setImageResource(imageId[position]);
            return rowView;
        }
    }