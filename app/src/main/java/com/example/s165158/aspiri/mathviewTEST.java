package com.example.s165158.aspiri;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.kexanie.library.MathView;

/**
 * Created by jonas on 21/11/2017.
 */

public class mathviewTEST extends AppCompatActivity {

    MathView formula_two;

    //Remember to use \\( CONTENT \\) instead of $$ CONTENT $$ for inline formulas  /makes everything easier
    String tex = "This come from string. You can insert inline formula:" +
            " \\(ax^2 + bx + c = 0\\) " +
            "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }

    @Override
    protected void onResume() {
        super.onResume();

        formula_two = (MathView) findViewById(R.id.formula_two);
        formula_two.setText(tex);

    }


}
