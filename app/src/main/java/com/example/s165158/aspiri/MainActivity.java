package com.example.s165158.aspiri;
import java.lang.*;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import static android.R.id.message;

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
        setContentView(R.layout.activity_navigation);
        subjectListArray = getResources().getStringArray(R.array.subject_list);
        subtextListArray = getResources().getStringArray(R.array.subtext_list);

        // Fanebladet
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Den flyvende knap nede i højre hjørne. BEMÆRKK Hvordan den laver et nyt view!
        FloatingActionButton floatingActionButton1 = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            // OnClick metode for den flyvende knap
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","jm@aspiri.dk",null));
                intent.putExtra(Intent.EXTRA_SUBJECT, R.string.Email_subject);
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.Email_beginning_1));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                System.out.println("Mail_icon pressed");

            }
        });

        //Navigation menu oppe til venstre
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
                Intent intent = new Intent(MainActivity.this, ClickOnList.class);
//                Laver en string variabel og positionerer den efter hvilken række i listen der blev valgt
                String message = subjectListArray[position];
                //Tilføjer nu strengen som en "extra" til Intent
                intent.putExtra("content", message);
                startActivity(intent);

            }
        });
    }


//    //Vides ikke. Justin kommenter pls

//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }


//    Tredottede menu initialiseres her
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

//    Onclick for tredottede menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_quit:
                finish();
                System.out.println("action_quit pressed");
                return true;

            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
                System.out.println("action_settings pressed");
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Toast.makeText(getApplicationContext(), "Action Favorite is yet to be implemented", Toast.LENGTH_SHORT).show();
                System.out.println("action_favorite pressed");
                return true;

            default:
//                Vi burde ikke kommer hertil, det betyder at der ikke har været et godkendt input fra brugeren af.
                return super.onOptionsItemSelected(item);
        }
    }

    //BURDE VI IKKE BRUGE SWITCH? Mindre kode
    // On click for de forskellige optioner i hamburgernavigation menuen.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Getting int for if statements
        int id = item.getItemId();

//        Hjem knappen - skal gå til hovedmenuen
        if (id == R.id.nav_home) {
            Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
            System.out.println("Pressed nav_home");

//        Butikken, til web
        } else if (id == R.id.nav_store) {
            Toast.makeText(getApplicationContext(), getString(R.string.opening_store), Toast.LENGTH_SHORT).show();
            System.out.println("Opening Webstore");
            String url = "https://karakterloeft.aspiri.dk/UI/Shopping/CourseIndex.aspx";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

//            Skal ikke være der
        } else if (id == R.id.nav_camera) {
            Toast.makeText(getApplicationContext(), "Camera is yet to be implemented", Toast.LENGTH_SHORT).show();
            System.out.println("Pressed Camera");

//            Skal ikke være der
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(), "Gallery is yet to be implemented", Toast.LENGTH_SHORT).show();
            System.out.println("Pressed Nav_Gallery");

//            Skal ikke være der
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(getApplicationContext(), "Slideshow/import is yet to be implemented", Toast.LENGTH_SHORT).show();
            System.out.println("Pressed Nav_Slideshow");

//            Skal ikke være der
        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(), "Manage/Tools is yet to be implemented", Toast.LENGTH_SHORT).show();
            System.out.println("Pressed Nav_Manage");

//            Share knappen. Skal integreres med facebook osv.
        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(), "Share is yet to be implemented", Toast.LENGTH_SHORT).show();
            System.out.println("Pressed Nav_Share");

        } else if (id == R.id.nav_send) {
            Toast.makeText(getApplicationContext(), "Send is yet to be implemented", Toast.LENGTH_SHORT).show();
            System.out.println("Pressed Nav_send");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


