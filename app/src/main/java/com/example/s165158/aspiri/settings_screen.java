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

    Button backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_screen);

        backButton = (Button)findViewById(R.id.SettingsBackButton);
        backButton.setOnClickListener(this);


        backButton = (Button)findViewById(R.id.SettingsBackButton);
        backButton.setOnClickListener(this);

    }


    //Knapper
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //Knap test udføres bare ved normale brugertest
            case R.id.SettingsBackButton:
                super.onBackPressed();
                Log.d("AspiriApp","back button pressed");
                break;

            default:
                break;
        }
    }

}
