package com.aspiri.karakterloeft.games.flipcards;

import android.app.Fragment;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspiri.karakterloeft.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import katex.hourglass.in.mathlib.MathView;

/**
 * A fragment representing the back of the card.
 */
public  class CardBackFragment extends Fragment {
    private AppCompatActivity mActivity;

    public CardBackFragment() {
    }


    TextView backTitle,backDescription;
    MathView mathView_back_title;
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
        mathView_back_title = view.findViewById(R.id.mathView_card_title);

        int index = ((FlipcardActivity)mActivity).getCurrentCardIndex();

        if(index < front.size()) {
            String stringBack = back.get(index);
            String stringBackExplanation = backExplanation.get(index);

            if (stringBack.contains("$$")){
                backTitle.setVisibility(View.GONE);
                mathView_back_title.setVisibility(View.VISIBLE);
                mathView_back_title.setDisplayText(stringBack);
            } else{
                mathView_back_title.setVisibility(View.GONE);
                backTitle.setVisibility(View.VISIBLE);
                backTitle.setText(stringBack);
            }

            backDescription.setText(stringBackExplanation);
        }

        return view;
    }

}

