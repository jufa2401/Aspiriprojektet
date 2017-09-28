package com.example.s165158.aspiri;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by s165158 on 27-09-2017.
 */

public class List extends AppCompatActivity {

    ListView lst;

    String[] nameArray = {"Rentes Regning", "Kvatratsætninger", "Potensregneregler", "Polynomier", "Lineær eksponentiel og potens-sammenhænge", "Statistik", "Differentialregning", "Integralregning", "Geometri", "Plangeometri med vektore", "Rumgeometri med vektore", "Areal, omkreds og rumfang", "Placeholder", "Placeholder", "Placeholder"};

    // Lavet som array så man kan tilføje flere beskrivelser
    String[] infoArray = {
            "Her lærer du om Sin, Cos og Tan", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri", "Trigonometri"
    };

    Integer[] imageArray = {R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
    };



    @Override
    public void onCreate (Bundle savedInstance) {

    }
}
