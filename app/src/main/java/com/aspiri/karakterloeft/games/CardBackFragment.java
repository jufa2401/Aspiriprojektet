package com.aspiri.karakterloeft.games;

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
 * A fragment representing the back of the card.
 */
public  class CardBackFragment extends Fragment {
    private AppCompatActivity mActivity;

    public CardBackFragment() {
    }


    TextView backTitle,backDescription;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_back, container, false);

        mActivity = (AppCompatActivity) getActivity();

        ArrayList<String> front = getArguments().getStringArrayList("front");
        ArrayList<String> back = getArguments().getStringArrayList("back");
        ArrayList<String> backExplanation = getArguments().getStringArrayList("backExplanation");

        backTitle = view.findViewById(R.id.back_title);
        backDescription = view.findViewById(R.id.back_description);

        int index = ((FlipcardActivity)mActivity).getCurrentCardIndex();

        if(index < front.size()) {
            String stringBack = back.get(index);
            String stringBackExplanation = backExplanation.get(index);

            backTitle.setText(stringBack);
            backDescription.setText(stringBackExplanation);
        }

        return view;
    }

}

