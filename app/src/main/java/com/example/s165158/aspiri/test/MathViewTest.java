package com.example.s165158.aspiri.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.s165158.aspiri.R;
import com.nishant.math.MathView;

public class MathViewTest extends AppCompatActivity {

    MathView mathView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_view_test);

        mathView = (MathView) findViewById(R.id.math_view);
        mathView.setText(getString(R.string.mathModeXMLTest1));

    }
}
