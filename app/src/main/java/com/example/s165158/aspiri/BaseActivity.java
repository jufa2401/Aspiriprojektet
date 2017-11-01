package com.example.s165158.aspiri;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.s165158.aspiri.fragments.MyListFragment;
import com.example.s165158.aspiri.other.activities.other.fragments.BlankFragment;
import com.example.s165158.aspiri.other.activities.other.fragments.MyListFragment222;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private FloatingActionButton floatingActionButton1;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    // Activate mToolbar
    protected Toolbar activateToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }

    protected FloatingActionButton activateFloatingActionButton() {
        // Den flyvende knap nede i højre hjørne. BEMÆRKK Hvordan den laver et nyt view!

        FloatingActionButton floatingActionButton1 = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            // OnClick metode for den flyvende knap
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jm@aspiri.dk", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.startMail));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));


                System.out.println("Mail_icon pressed");
            }
        });
        return floatingActionButton1;
    }

    protected NavigationView activateNavigationView() {
        //Navigation menu oppe til venstre
        if (drawer == null) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer != null) {
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.addDrawerListener(toggle);
                toggle.syncState();
            }
            if (navigationView == null) {
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                if (navigationView != null) {
                    navigationView.setNavigationItemSelectedListener(this);
                }
            }
        }
        return navigationView;
    }

    //    On createmetode
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        activateToolbar();
        activateFloatingActionButton();
        activateNavigationView();

        if(savedInstanceState == null) {
//        Instance of list_view fragment
            Fragment listFragment = new MyListFragment();
            ListFragment list2Fragment = new MyListFragment222();

            //           other.... not working
            Fragment blankFragment = new BlankFragment();


//            add Fragment to FrameLayout (container) using FragmentManager *Does not appear visually?? control with TA*
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragmentindhold, listFragment);

//           Subject fragment
//            ft.add(R.id.fragmentindhold, blankFragment);

            ft.commit();
        }
    }



    // Sætter draweren til at lukke med tilbageklik(hw) rettere end at at lukke applikationen
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    // OnClick for Settings and favorite options!
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

                Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
                System.out.println("action_favorite pressed");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.

                return super.onOptionsItemSelected(item);
        }
    }

    // On click for de forskellige optioner i navigation menuen.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {


            Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_home) {
            Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_store) {
            Toast.makeText(getApplicationContext(), "Yet to be implemented", Toast.LENGTH_SHORT).show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





}


