package com.aspiri.karakterloeft.games.flipcards;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspiri.karakterloeft.R;

import java.util.ArrayList;

/**
 * Created by Justin on 12/01/2018.
 */
public  class CardFrontFragment extends Fragment {
    TextView textFront;

    public CardFrontFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card_front, container, false);

        AppCompatActivity mActivity = (AppCompatActivity) getActivity();
        textFront = view.findViewById(R.id.card_question);

        Bundle b = getArguments();
        ArrayList<String> front = b.getStringArrayList("front");

        ArrayList<String> back = getArguments().getStringArrayList("back");

       int index = ((FlipcardActivity)mActivity).getCurrentCardIndex();

        if(index < front.size()) {
            String stringFront = front.get(index);

            textFront.setText(stringFront);
        }
        return view;
    }






}
