package com.example.s165158.aspiri;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;


import com.example.s165158.aspiri.test.TestFlipcard;
import com.example.s165158.aspiri.test2.ListFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final static String MESSAGE = "MESSAGE";

    ListFragment listFragment;
    Toolbar toolbar;

    ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    //On createmetode
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Fanebladet
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Drawer menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // get the listview
        //expandableListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        //prepareListData();

        //Creating the list adapter from class
        //listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);

        // setting list adapter
        //expandableListView.setAdapter(listAdapter);

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
            case android.R.id.home:


                return true;

            case R.id.three_dot_quit:
                finish();
                Log.d("AspiriApp", "action_quit pressed");
                return true;

            case R.id.three_dot_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
                Log.d("AspiriApp", "action_settings pressed");
                return true;

            case R.id.three_dot_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite..  .
                Toast.makeText(getApplicationContext(), "Action Favorite is yet to be implemented", Toast.LENGTH_SHORT).show();
                Log.d("AspiriApp", "action_favorite pressed");
                return true;

            //Should be unused at all times
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // On click for de forskellige optioner i drawer.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            //        Top functions
            case R.id.drawer_home:  // SKAL LUKKE DRAWEREN UDEN AT BRUGERN TRÆKKER DEN IND
                Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
                Log.d("AspiriApp","HoHome Button pressed");
                return true;

            case R.id.drawer_store:
                Intent goToStore = new Intent(Intent.ACTION_VIEW);
                goToStore.setData(Uri.parse(getString(R.string.drawer_URL_ToTheStore)));
                startActivity(goToStore);
                Log.d("AspiriApp","Webstore pressed");
                return true;

            case R.id.drawer_send_us_mail:
                Intent sendUsMail= new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jm@man_at_blackboad_round.dk", null));
                sendUsMail.putExtra(Intent.EXTRA_SUBJECT, R.string.mail_subject);
                sendUsMail.putExtra(Intent.EXTRA_TEXT, getString(R.string.mail_beginning));
                startActivity(Intent.createChooser(sendUsMail, "Choose an Email client :"));
                Log.d("AspiriApp", "Mail_icon pressed");
                return true;

            //Goes to flipcards.
            case R.id.drawer_flipcards:
                Intent goToFlipCards = new Intent(this,TestFlipcard.class);
                startActivity(goToFlipCards);
                Log.d("AspiriApp","Flipcard_pressed");
                return true;

            //        Maps Navigation
            case R.id.drawer_Kbh:
                Intent goToSigurdsGade = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.drawer_URL_KBH)));
                startActivity(goToSigurdsGade);
                Log.d("AspiriApp", "drawer_KBH pressed");
                return true;

            case R.id.drawer_Aarhus:
                Intent goToAarhus = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.drawer_URL_Aarhus)));
                startActivity(goToAarhus);
                Log.d("AspiriApp", "drawer_Aarhus pressed");
                return true;

            case R.id.drawer_Odense:
                Intent goToOdense = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.drawer_URL_Odense)));
                startActivity(goToOdense);
                Log.d("AspiriApp", "drawer_Odense pressed");
                return true;

            case R.id.drawer_HQ:
                Intent goToFalonerAlle = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.drawer_URL_HQ)));
                startActivity(goToFalonerAlle);
                Log.d("AspiriApp", "drawer_HQ pressed");
                return true;


            //Tests
            case R.id.go_to_subject1:

                Intent goToTest1 = new Intent(this, mathviewTEST.class);
                startActivity(goToTest1);
//                Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
                return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onResume() {
        super.onResume();
        listFragment = new ListFragment();
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentindhold, listFragment)
                .commit();

    }
//    TODO: FIX HOME AS UP
    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if(count ==0){
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");
        listDataHeader.add("Testeri");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        List<String> Testeri = new ArrayList<String>();
        Testeri.add("noget 1");
        Testeri.add("noget 2");
        Testeri.add("noget 3");
        Testeri.add("noget 4");

        // Header, Child data
        listDataChild.put(listDataHeader.get(0), top250);
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3), Testeri);
    }
}





