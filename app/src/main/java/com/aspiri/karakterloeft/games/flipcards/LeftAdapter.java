package com.aspiri.karakterloeft.games.flipcards;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspiri.karakterloeft.R;
import com.yalantis.multiselection.lib.adapter.BaseLeftAdapter;

//import com.aspiri.karakterloeft.games.Contact;


/**
 * Created by Artem Kholodnyi on 9/3/16.
 */
public class LeftAdapter extends BaseLeftAdapter<Flipcard, ViewHolder> {

    private final Callback callback;

    public LeftAdapter(Callback callback) {
        super(Flipcard.class);
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        ViewHolder.bind(holder, getItemAt(position));

        holder.itemView.setOnClickListener(view -> {
            view.setPressed(true);
            view.postDelayed(() -> {
                view.setPressed(false);
                callback.onClick(holder.getAdapterPosition());
            }, 200);
        });

    }

}
