package com.example.s165158.aspiri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by jonas on 12/10/2017.
 */



public class settings_screen extends Activity implements View.OnClickListener {

    //Initializing button
    Button backButton;


    //Oncreate method
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Setting xml file view
        setContentView(R.layout.settings_screen);

        //Casting button to button and setting onclicklistener
        backButton = findViewById(R.id.SettingsBackButton);
        backButton.setOnClickListener(this);

    }


    //Buttons onClick methods
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.SettingsBackButton:
                super.onBackPressed();
                Log.d("AspiriApp","back button pressed");
                break;

            default:
                break;
        }
    }

}
