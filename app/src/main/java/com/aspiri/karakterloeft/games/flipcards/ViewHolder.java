package com.aspiri.karakterloeft.games.flipcards;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspiri.karakterloeft.R;

//import com.aspiri.karakterloeft.games.Contact;

/**
 * Created by Artem Kholodnyi on 9/6/16.
 */
class ViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    ImageView avatar;

    public ViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.name);
//        avatar = (ImageView) view.findViewById(R.id.yal_ms_avatar);
    }

    public static void bind(ViewHolder viewHolder, Flipcard flipcard) {
        viewHolder.name.setText(flipcard.getCategory());
//        viewHolder.avatar.setImageURI(contact.getPhotoUri());
    }
}