package com.example.s165158.aspiri;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;


/**
 * Created by s165158 on 28-09-2017.
 */

class OnNavigationItemSelectedListener extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


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
