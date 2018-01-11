package com.aspiri.karakterloeft.games;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aspiri.karakterloeft.R;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by janus on 1/11/2018.
 */

public class FlipCardButtonFragment extends Fragment {

    @BindViews({R.id.flipcard_button_previous,R.id.flipcard_button_next})
    Button buttons[];

    @OnClick(R.id.flipcard_button_previous)
    void onClickPrevious(){
        //TODO: Lav flere flipcards, der skiftes imellem.
    }
    @OnClick(R.id.flipcard_button_next)
    void onClickNext(){
        //TODO: Lav flere flipcards, der skiftes imellem.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flipcard_buttons,container,false);
        ButterKnife.bind(view);

        return view;
    }
}
