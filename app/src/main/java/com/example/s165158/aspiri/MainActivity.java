package com.example.s165158.aspiri;
import java.lang.*;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final static String MESSAGE = "MESSAGE";
    ListView lst;


    //instantierer Arrays så det kan bruges uden for klassen, og refereres til fra Strings
    String[] subjectListArray;
    String[] subtextListArray;

    //Skal laves om!
    Integer[] imageArray = {R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
    };


    //On createmetode
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        subjectListArray = getResources().getStringArray(R.array.subject_list);
        subtextListArray = getResources().getStringArray(R.array.subtext_list);

        // Fanebladet
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Drawer menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Objekt for den scrollende liste
        CustomListAdapter whatever = new CustomListAdapter(this, subjectListArray, subtextListArray, imageArray);
        lst = (ListView) findViewById(R.id.list);
        lst.setAdapter(whatever);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "ID: " + id, Toast.LENGTH_SHORT).show();
//                Intent er hvad man bruger til at skifte imellem activities
                Intent openClickOnList = new Intent(MainActivity.this, Subject2.class);

//                Laver en string variabel og positionerer den efter hvilken række i listen der blev valgt
                String message = subjectListArray[position];
                //Tilføjer nu strengen som en "extra" til Intent
                openClickOnList.putExtra("content", message);
                startActivity(openClickOnList);

            }
        });
    }


//    Tredottede menu initialiseres her
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.three_dot_menu, menu);
        return true;
    }

//    Onclick for tredottede menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.three_dot_quit:
                finish();
                Log.d("AspiriApp","action_quit pressed");
                return true;

            case R.id.three_dot_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
                Log.d("AspiriApp","action_settings pressed");

                Intent goToSettings = new Intent(this, settings_screen.class);
                startActivity(goToSettings);

                return true;

            case R.id.three_dot_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Toast.makeText(getApplicationContext(), "Action Favorite is yet to be implemented", Toast.LENGTH_SHORT).show();
                Log.d("AspiriApp","action_favorite pressed");
                return true;

                //Should be unused at all times
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // On click for de forskellige optioner i hamburgernavigation menuen.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Getting int for if statements
        int id = item.getItemId();

//        Home button, returns to landing page
        if (id == R.id.drawer_home) {
            Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
            Log.d("AspiriApp","Pressed nav_home");

//        Store, goes to webview
        } else if (id == R.id.drawer_store) {
            Toast.makeText(getApplicationContext(), getString(R.string.drawer_store), Toast.LENGTH_SHORT).show();
            Log.d("AspiriApp","Opening Webstore");
            String url = "https://karakterloeft.aspiri.dk/UI/Shopping/CourseIndex.aspx";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

//            Remove this
        } else if (id == R.id.drawer_camera) {
            Toast.makeText(getApplicationContext(), "Camera is yet to be implemented", Toast.LENGTH_SHORT).show();
            Log.d("AspiriApp","Pressed Camera");

//            Remove this
        } else if (id == R.id.drawer_gallery) {
            Toast.makeText(getApplicationContext(), "Gallery is yet to be implemented", Toast.LENGTH_SHORT).show();
            Log.d("AspiriApp","Pressed Nav_Gallery");


//            Stringen er ændret til flipcard for at arbejde videre med UI, som test.
        } else if (id == R.id.drawer_slideshow) {
            Toast.makeText(getApplicationContext(), "Placeholder for flipcards/vendekort", Toast.LENGTH_SHORT).show();
            Log.d("AspiriApp","Pressed Nav_Slideshow");
            Intent gotoTestFlipcard = new Intent(this, com.example.s165158.aspiri.test.TestFlipcard.class);
            startActivity(gotoTestFlipcard);

//            Skal ikke være der
        } else if (id == R.id.drawer_manage) {
            Toast.makeText(getApplicationContext(), "Manage/Tools is yet to be implemented", Toast.LENGTH_SHORT).show();
            Log.d("AspiriApp", "Pressed Nav_Manage");
// Comment: (intern) - Hvad skal dette benyttes til?
//            case R.id.drawer_Odense:
//                Intent goToOdense = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.drawer_Odense_to_maps)));
//                startActivity(goToOdense);
//                Log.d("AspiriApp", "drawer_Odense pressed");
//                return true;

//            Share knappen. Skal integreres med facebook osv.
        } else if (id == R.id.drawer_share) {
            Toast.makeText(getApplicationContext(), "Share is yet to be implemented", Toast.LENGTH_SHORT).show();
            Log.d("AspiriApp","Pressed Nav_Share");

        } else if (id == R.id.drawer_send) {
            Toast.makeText(getApplicationContext(), "Send is yet to be implemented", Toast.LENGTH_SHORT).show();
            Log.d("AspiriApp","Pressed Nav_send");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


