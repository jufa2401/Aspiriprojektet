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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final static String MESSAGE = "MESSAGE";

    com.example.s165158.aspiri.test2.ListFragment listFragment;

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


        // Fanebladet
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Drawer menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listFragment = new com.example.s165158.aspiri.test2.ListFragment();
// Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentindhold, listFragment)
                .commit();



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
                Log.d("AspiriApp", "action_quit pressed");
                return true;

            case R.id.three_dot_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
                Log.d("AspiriApp", "action_settings pressed");

                Intent goToSettings = new Intent(this, settings_screen.class);
                startActivity(goToSettings);

                return true;

            case R.id.three_dot_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Toast.makeText(getApplicationContext(), "Action Favorite is yet to be implemented", Toast.LENGTH_SHORT).show();
                Log.d("AspiriApp", "action_favorite pressed");
                return true;

            //Should be unused at all times
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // On click for de forskellige optioner i hamburgernavigation menuen.
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
                goToStore.setData(Uri.parse(getString(R.string.drawer_store_url)));
                startActivity(goToStore);
                Log.d("AspiriApp","Webstore pressed");
                return true;

            case R.id.drawer_send_us_mail:
                Intent sendUsMail= new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jm@man_at_blackboad_round.dk", null));
                sendUsMail.putExtra(Intent.EXTRA_SUBJECT, R.string.mail_subject);
                sendUsMail.putExtra(Intent.EXTRA_TEXT, getString(R.string.mail_beginning));
                startActivity(Intent.createChooser(sendUsMail, "Choose an Email client :"));
                Log.d("AspiriApp", "Mail_icon pressed");



                //        Maps Navigation
            case R.id.drawer_Kbh:
                Intent goToSigurdsGade = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.drawer_kbh_to_maps)));
                startActivity(goToSigurdsGade);
                Log.d("AspiriApp", "drawer_KBH pressed");
                return true;

            case R.id.drawer_Aarhus:
                Intent goToAarhus = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.drawer_Aarhus_to_maps)));
                startActivity(goToAarhus);
                Log.d("AspiriApp", "drawer_Aarhus pressed");
                return true;

            case R.id.drawer_Odense:
                Intent goToOdense = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.drawer_Odense_to_maps)));
                startActivity(goToOdense);
                Log.d("AspiriApp", "drawer_Odense pressed");
                return true;

            case R.id.drawer_HQ:
                Intent goToFalonerAlle = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.drawer_HQ_to_maps)));
                startActivity(goToFalonerAlle);
                Log.d("AspiriApp", "drawer_HQ pressed");
                return true;


                //Tests
            case R.id.go_to_subject1:
                Intent goToSubject1 = new Intent(this, Subject1.class);
                startActivity(goToSubject1);
                Log.d("AspiriApp", "going to subject 1");
                return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}





