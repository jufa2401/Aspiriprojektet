package com.example.s165158.aspiri;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import io.github.kexanie.library.MathView;

/**
 * Created by jonas on 21/11/2017.
 */

public class mathviewTEST extends Fragment {

    MathView formula_two;
    private ExpandableTextView expandableTextView1, expandableTextView2;

    //Remember to use \\( CONTENT \\) instead of $$ CONTENT $$ for inline formulas  /makes everything easier
    String tex = "This come from string. You can insert inline formula:" +
            " \\(ax^2 + bx + c = 0\\) " +
            "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$";


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test);
        View view = inflater.inflate(R.layout.test, container, false);

        formula_two = (MathView) view.findViewById(R.id.formula_two);
        formula_two.setText(tex);

//        expandableTextView1 = (ExpandableTextView) view.findViewById(R.id.expand_text_view1);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
