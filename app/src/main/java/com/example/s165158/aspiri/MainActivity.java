package com.example.s165158.aspiri;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Navigation {
    ListView lst;

    String[] nameArray = {"Rentes Regning","Kvatratsætninger","Potensregneregler","Polynomier","Lineær eksponentiel og potens-sammenhænge","Statistik","Differentialregning","Integralregning","Geometri","Plangeometri med vektore","Rumgeometri med vektore","Areal, omkreds og rumfang","Placeholder","Placeholder","Placeholder"};

    // Lavet som array så man kan tilføje flere beskrivelser
    String[] infoArray = {
            "Her lærer du om Sin, Cos og Tan","Trigonometri","Trigonometri","Trigonometri","Trigonometri","Trigonometri","Trigonometri","Trigonometri","Trigonometri","Trigonometri","Trigonometri","Trigonometri","Trigonometri","Trigonometri","Trigonometri"
    };

    Integer[] imageArray = {R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig,R.drawable.trig
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fanebladet
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, infoArray, imageArray);
        lst  = (ListView) findViewById(R.id.list);
        lst.setAdapter(whatever);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
